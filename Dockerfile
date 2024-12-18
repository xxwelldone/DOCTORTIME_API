#FROM amazoncorretto:17-alpine-jdk
#
#WORKDIR /clines
#COPY target/*.jar /clines/app.jar
#EXPOSE 8080
#CMD java -XX:+UseContainerSupport -jar app.jar

FROM maven:3.9.9-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests

FROM openjdk:17-ea-10-jdk-slim
WORKDIR /app

COPY --from=build ./app/target/*.jar ./doctortime.jar
EXPOSE 8080
ENTRYPOINT java -jar doctortime.jar