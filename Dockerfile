FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY target/bookstore-${IMAGE_NAME}.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
