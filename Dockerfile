FROM openjdk:8-jre-alpine
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]