FROM openjdk:17-jdk
MAINTAINER "Nick litjens"
EXPOSE 80
COPY target/routeservice-0.0.1-SNAPSHOT.jar routeservice-0.0.1.jar
ENTRYPOINT ["java", "-jar", "api-0.0.1.jar"]