FROM maven:3.9-eclipse-temurin-17 AS build

COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

COPY --from=build /target/cryptoviewer-0.0.1-SNAPSHOT.jar cryptoviewer.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cryptoviewer.jar"]
