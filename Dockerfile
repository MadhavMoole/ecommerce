FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/Ecommerce-0.0.1-SNAPSHOT.jar app.jar
LABEL authors="Madha"

ENTRYPOINT ["java", "-jar", "/app.jar"]