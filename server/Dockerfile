FROM maven:3.8.2-jdk-11-slim AS build
RUN mkdir -p /workspace/app
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN mvn -f pom.xml clean package -DskipTests

FROM openjdk:11
COPY --from=build /workspace/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]