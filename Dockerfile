FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar

# TODO: change this to run on ci
RUN ./gradlew build

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]