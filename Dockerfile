# ==========================================
# BUILD STAGE
# ==========================================
FROM maven:3.10.0-amazoncorretto-21-debian AS maven_build

WORKDIR /build

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests


# ==========================================
# RUNTIME STAGE
# ==========================================
FROM amazoncorretto:21-headless

WORKDIR /app

COPY --from=maven_build --chown=1000:1000 \
    /build/target/typer-*.jar app.jar

USER 1000:1000

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
