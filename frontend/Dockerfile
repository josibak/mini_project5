# 1단계: Build Stage
FROM node:18 AS builder

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build:docker

# 2단계: Production Stage
FROM nginx:alpine

# 빌드 결과물을 nginx html 폴더로 복사
COPY --from=builder /app/dist /usr/share/nginx/html

# SPA 라우팅을 위한 nginx 설정 파일 복사 (아래 nginx.conf 참고)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 8080

CMD ["nginx", "-g", "daemon off;"]
