FROM ubuntu:latest AS build

RUN apt-get update 
RUN apt-get install openjdk-19-jdk -y
COPY . .

RUN apt-get install maven -y

ENV MAVEN_SSL_INSECURE=true
RUN mvn clean install -Dspring.profiles.active=prod -Dmaven.wagon.http.ssl.insecure=$MAVEN_SSL_INSECURE

FROM openjdk:19-jdk-alpine

ENV PORT=8080
EXPOSE $PORT

COPY --from=build /target/byte-bot.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
