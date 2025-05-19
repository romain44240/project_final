FROM maven:3.9.9-amazoncorretto-21-debian-bookworm AS build

WORKDIR /app

COPY . .

RUN mvn clean package

FROM openjdk:21-jdk-bullseye

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080