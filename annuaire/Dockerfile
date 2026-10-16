# Étape 1 : compilation avec Gradle
FROM gradle:8-jdk21 AS build
WORKDIR /build
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
RUN chmod +x gradlew
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test \
    && mv $(ls build/libs/*.jar | grep -v plain) app.jar

# Étape 2 : image finale légère
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /build/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]