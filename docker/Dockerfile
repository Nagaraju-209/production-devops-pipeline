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
LABEL project="Production DevOps Pipeline"

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","app.jar"]