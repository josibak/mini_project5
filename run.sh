#!/bin/bash

# 공통 설정
NETWORK="infra_default"
PROFILE="docker"

# 공통 서비스 목록 불러오기
source "$(dirname "$0")/services.sh"

for SERVICE in "${SERVICES[@]}"; do
  read -r DIR IMAGE CONTAINER PORT <<< "$SERVICE"

  echo ""
  echo "🚀 [${DIR}] 빌드 및 컨테이너 실행 중..."

  # 디렉토리 이동
  cd "$DIR" || { echo "❌ 디렉토리 없음: $DIR"; exit 1; }

  # Maven 빌드
  mvn clean package -DskipTests || { echo "❌ Maven 빌드 실패: $DIR"; exit 1; }

  # 도커 이미지 빌드
  docker build -t "$IMAGE:local" . || { echo "❌ Docker 빌드 실패: $DIR"; exit 1; }

  # 기존 컨테이너 삭제
  docker rm -f "$CONTAINER" 2>/dev/null

  # 도커 실행
  docker run -d --name "$CONTAINER" \
    -p "$PORT:8080" \
    --network "$NETWORK" \
    -e SPRING_PROFILES_ACTIVE="$PROFILE" \
    "$IMAGE:local"

  # 원래 경로로 복귀
  cd - >/dev/null
done

echo ""
echo "✅ 모든 마이크로서비스가 실행되었습니다."
