FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle assemble

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=builder /app/build/libs/DiplomnaBackend-0.0.1-SNAPSHOT.jar app.jar
COPY fast-pedals-firebase-adminsdk.json .
EXPOSE 9090
CMD ["java", "-jar", "app.jar", "-Dspring.profiles.active=dockerembbed,oauth-security"]