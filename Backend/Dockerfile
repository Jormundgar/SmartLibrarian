FROM maven:3.8.3-eclipse-temurin-17 AS BUILD
COPY Backend /home/Backend
COPY Backend/pom.xml /home/Backend
RUN mvn -f /home/Backend/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=BUILD home/Backend/target/Backend-1.1.1-SNAPSHOT.jar backend-slp.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "backend-slp.jar"]