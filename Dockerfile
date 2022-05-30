FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY target/bookstore-${IMAGE_NAME}-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
