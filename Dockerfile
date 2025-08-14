# ====== Étape 1 : build Maven (Java 17) ======
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# On télécharge les dépendances d'abord pour accélérer les builds suivants
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests dependency:go-offline

# On copie le code et on package
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -B -DskipTests package -Dgpg.skip

# ====== Étape 2 : image exécution légère (JRE 17) ======
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Variables utiles (à modifier si besoin)
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS=""

# On copie le jar généré par Maven
# (nom issu de ton pom.xml : cosf-0.0.1-SNAPSHOT.jar)
COPY --from=build /app/target/cosf-0.0.1-SNAPSHOT.jar /app/app.jar

# Port Spring par défaut
EXPOSE 8080

# Si tu utilises Spring Actuator, tu pourras ajouter un HEALTHCHECK plus tard
# HEALTHCHECK --interval=30s --timeout=3s --start-period=20s \
#   CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
