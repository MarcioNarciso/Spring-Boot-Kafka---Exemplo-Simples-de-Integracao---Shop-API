FROM eclipse-temurin:20-alpine

VOLUME /tmp
ARG JAR_FILE=target/shop-api-0.0.1-SNAPSHOT.jar
ENV KAFKA_URL=localhost:9092
COPY ${JAR_FILE} app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]