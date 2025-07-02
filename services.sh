#!/bin/bash

# 서비스별 정보 정의 (디렉토리명, 이미지명, 컨테이너명, 외부 포트)
# 이 파일은 다른 스크립트에서 source 명령어로 불러와 사용됩니다.
SERVICES=(
  "gateway gateway gateway 8080"
  "aipublication aipublication aipublication 8084"
  "authormanagement authormanagement authormanagement 8082"
  "book book book 8085"
  "manuscript manuscript manuscript 8083"
  "point point point 8088"
  "subcription subcription subcription 8087"
  "usermanagement usermanagement usermanagement 8086"
)
