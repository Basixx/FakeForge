FROM openjdk:21-jdk-slim

ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

COPY build/libs/FakeForge-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080 5005

ENTRYPOINT ["java", "-jar", "app.jar"]
