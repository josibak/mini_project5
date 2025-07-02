FROM openjdk:15-jdk-alpine

# 앱 복사
COPY target/*SNAPSHOT.jar app.jar

# 포트 오픈
EXPOSE 8085

# 타임존 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# H2 Console remote 허용 설정 추가
ENV JAVA_TOOL_OPTIONS="-Dh2.console.settings.web-allow-others=true"

# 앱 실행
ENTRYPOINT ["java", "-Xmx400M", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=docker"]
