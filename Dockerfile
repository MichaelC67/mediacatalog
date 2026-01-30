# Multi-stage Dockerfile for a lightweight Spring Boot app image
# Build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy sources
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Build the fat jar (skip tests for faster builds; remove -DskipTests to run tests)
RUN mvn -B package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy jar from build stage and rename to app.jar
COPY --from=build /workspace/target/*.jar /app/app.jar

# Default directories (can be overridden at runtime with env vars)
ENV APP_UPLOAD_DIR=/app/uploads
ENV DB_DIR=/app/data

# Default spring datasource url (can be overridden by passing SPRING_DATASOURCE_URL env)
ENV SPRING_DATASOURCE_URL=jdbc:h2:file:${DB_DIR}/mediacatalogdb;AUTO_SERVER=TRUE

# Create mount points
VOLUME ["/app/uploads", "/app/data"]

# Expose app port
EXPOSE 8080

# Run the application; pass configured properties via system properties so Spring picks them up
ENTRYPOINT ["sh", "-c", "exec java -Dspring.datasource.url=\"${SPRING_DATASOURCE_URL}\" -Dapp.upload.dir=\"${APP_UPLOAD_DIR}\" -jar /app/app.jar"]
