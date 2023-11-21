FROM maven:3.8.3-eclipse-temurin-17 AS BUILD
COPY Frontend /home/Frontend
COPY Frontend/pom.xml /home/Frontend
RUN mvn -f /home/Frontend/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=BUILD home/Frontend/target/Frontend-0.0.1-SNAPSHOT.jar frontend-slp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "frontend-slp.jar"]