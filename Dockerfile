FROM gradle:8.13.0-jdk21 AS builder

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle /app/gradle

RUN ./gradlew dependencies

COPY . .

RUN ./gradlew clean build -x test

FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y telnet ca-certificates && update-ca-certificates

ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

WORKDIR /app

COPY --from=builder /app/build/libs/FakeForge-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080 5005

ENTRYPOINT ["java", "-jar", "app.jar"]
