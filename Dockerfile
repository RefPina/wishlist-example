# Use a imagem base do Maven para construir o projeto
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use a imagem base do OpenJDK para rodar o projeto
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/wishlist-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
