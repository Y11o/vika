FROM maven:3.9.6-eclipse-temurin-8
LABEL authors="yllo"

VOLUME /tmp
COPY ./target/*.jar ./app.jar
ENTRYPOINT ["java","-jar","./app.jar"]