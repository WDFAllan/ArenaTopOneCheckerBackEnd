# Étape 1 : Build avec Maven (optionnel si tu builds déjà en local)
# Si tu build localement, tu peux retirer cette étape et juste copier le JAR

FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Exécution
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copier le JAR dans l'image (remplace le chemin si tu build local)
COPY app/target/app-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port 8080 (port par défaut Spring Boot)
EXPOSE 8080


# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
