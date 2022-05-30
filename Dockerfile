FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
ARG BUILD_ID
COPY /var/jenkins_home/workspace/BookstorePipeline/target/bookstore-${BUILD_ID}.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]