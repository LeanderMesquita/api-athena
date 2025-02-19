FROM openjdk:21-jdk-slim

LABEL authors="Leander"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app.jar

WORKDIR /athena

COPY .env .env

EXPOSE 8085

RUN echo "#!/bin/sh" > /build.sh \
    && echo 'export $(grep -v "^#" .env | xargs)' >> /deploy.sh \
    && echo 'exec java -jar /app.jar' >> /build.sh \
    && chmod +x /build.sh

ENTRYPOINT ["/build.sh"]