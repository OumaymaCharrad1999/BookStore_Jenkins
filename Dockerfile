FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY target/bookstore-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]