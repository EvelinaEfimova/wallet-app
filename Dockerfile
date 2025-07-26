FROM amazoncorretto:17-alpine

WORKDIR /app

COPY target/wallet-app-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
