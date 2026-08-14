# Build stage
FROM maven:3.9.4-amazoncorretto-21-debian-bookworm AS maven_build
WORKDIR /build
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
RUN useradd -m -u 1000 appuser
COPY --from=maven_build --chown=appuser:appuser /build/target/typer-*.jar app.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
