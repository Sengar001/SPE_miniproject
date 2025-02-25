FROM openjdk:11
COPY target/SPE_miniproject-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
