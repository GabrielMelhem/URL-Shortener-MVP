# Stage 1: Build the application
FROM maven:3.9.4-openjdk-21 AS builder


WORKDIR /app

COPY pom.xml .
COPY app/pom.xml app/
COPY boundary/pom.xml boundary/
COPY domain/pom.xml domain/
COPY store/pom.xml store/

COPY . .

RUN ./mvnw clean install -DskipTests

FROM openjdk:21-jdk

WORKDIR /app

COPY app/target/app-0.0.1-SNAPSHOT.war app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.war"]

