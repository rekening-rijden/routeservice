FROM openjdk:17-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} tweet-service.jar

ENTRYPOINT ["java", "-jar", "tweet-service.jar"]