# ==========================
# Stage 1 - Build
# ==========================
FROM maven:3.9.8-eclipse-temurin-17 AS builder

WORKDIR /build

COPY app/springboot-app/pom.xml .

RUN mvn dependency:go-offline

COPY app/springboot-app/src ./src

RUN mvn clean package -DskipTests

# ==========================
# Stage 2 - Runtime
# ==========================
FROM eclipse-temurin:17-jre

LABEL maintainer="Dandu Rama Siva Naga Raju"

LABEL org.opencontainers.image.title="Production DevOps Pipeline"

LABEL org.opencontainers.image.version="1.0.0"

LABEL org.opencontainers.image.description="Spring Boot DevOps Production Pipeline"

LABEL org.opencontainers.image.authors="Dandu Rama Siva Naga Raju"

LABEL org.opencontainers.image.source="https://github.com/nagarajucsd/production-devops-pipeline"

WORKDIR /app

# Create non-root user
RUN groupadd -r spring && useradd -r -g spring spring

# Copy the jar and change its ownership to the 'spring' user
COPY --from=builder  /build/target/*.jar app.jar

RUN chown spring:spring app.jar

USER spring

EXPOSE 8082

# Define health probe before launching the application
HEALTHCHECK --interval=30s \
--timeout=5s \
--start-period=20s \
--retries=3 \
CMD wget --no-verbose --tries=1 --spider http://localhost:8082/actuator/health || exit 1

ENTRYPOINT ["java","-jar","app.jar"]
