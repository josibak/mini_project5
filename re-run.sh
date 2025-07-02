#!/bin/bash
set -euo pipefail

# 공통 설정
NETWORK="infra_default"
PROFILE="docker"

# 공통 서비스 목록 불러오기
source "$(dirname "$0")/services.sh"

# 사용 가능한 서비스 목록을 동적으로 생성하고 사용법을 출력하는 함수
print_usage() {
  echo "❌ 사용법: $0 <service_name>"
  echo "사용 가능한 서비스 (디렉토리명 또는 접두사):"
  for SERVICE in "${SERVICES[@]}"; do
    read -r DIR _ _ _ <<< "$SERVICE"
    PREFIX=$(echo "$DIR" | cut -d'_' -f1)
    echo "  - $DIR (접두사: $PREFIX)"
  done
}

# 인자가 없는 경우 사용법 출력 후 종료
if [ -z "$1" ]; then
  print_usage
  exit 1
fi

TARGET_SERVICE=$1
SERVICE_FOUND=false

for SERVICE in "${SERVICES[@]}"; do
  read -r DIR IMAGE CONTAINER PORT <<< "$SERVICE"
  PREFIX=$(echo "$DIR" | cut -d'_' -f1)

  # 입력받은 서비스 이름과 디렉토리 이름 또는 접두사가 일치하는지 확인
  if [ "$DIR" == "$TARGET_SERVICE" ] || [ "$PREFIX" == "$TARGET_SERVICE" ]; then
    SERVICE_FOUND=true
    
    echo ""
    echo "🚀 [${DIR}] 재빌드 및 컨테이너 재실행을 시작합니다..."

    # 디렉토리 이동
    if ! cd "$DIR"; then
      echo "❌ 디렉토리 이동 실패: $DIR"
      exit 1
    fi

    # Maven 빌드
    echo "   - Maven 빌드를 시작합니다."
    if ! mvn clean package -DskipTests; then
      echo "❌ Maven 빌드 실패: $DIR"
      cd - >/dev/null
      exit 1
    fi
    echo "   - Maven 빌드 완료."

    # 기존 컨테이너 중지 및 삭제
    echo "   - 기존 컨테이너를 중지하고 삭제합니다: $CONTAINER"
    docker rm -f "$CONTAINER" 2>/dev/null || true

    # 기존 이미지 삭제
    echo "   - 기존 Docker 이미지를 삭제합니다: $IMAGE:local"
    docker rmi -f "$IMAGE:local" 2>/dev/null || true

    # 도커 이미지 빌드
    echo "   - 새 Docker 이미지를 빌드합니다."
    if ! docker build -t "$IMAGE:local" .; then
      echo "❌ Docker 이미지 빌드 실패: $DIR"
      cd - >/dev/null
      exit 1
    fi
    echo "   - Docker 이미지 빌드 완료."

    # 도커 컨테이너 실행
    echo "   - 새 Docker 컨테이너를 실행합니다."
    docker run -d --name "$CONTAINER" \
      -p "$PORT:8080" \
      --network "$NETWORK" \
      -e SPRING_PROFILES_ACTIVE="$PROFILE" \
      "$IMAGE:local"

    # 원래 경로로 복귀
    cd - >/dev/null

    echo ""
    echo "✅ [${DIR}] 서비스가 성공적으로 재실행되었습니다."
    break
  fi
done

# 일치하는 서비스를 찾지 못한 경우
if [ "$SERVICE_FOUND" = false ]; then
  echo "❌ 서비스를 찾을 수 없습니다: $TARGET_SERVICE"
  print_usage
  exit 1
fi
