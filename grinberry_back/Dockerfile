FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY mvnw pom.xml ./

COPY ./src ./src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY .env .env

EXPOSE 8443

COPY --from=builder /app/target/*.jar /app/app.jar

COPY src/main/resources/ssl/grinberry_ssl.pfx grinberry_ssl.pfx

ENTRYPOINT ["java","-jar","app.jar", "--server.ssl.key-store=grinberry_ssl.pfx", "--server.ssl.key-store-password=${SSL_PASSWORD}", "--server.ssl.keyStoreType=PKCS12", "--server.ssl.enabled=true", "--server.port=8443"]