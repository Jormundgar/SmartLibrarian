FROM maven:3.8.3-eclipse-temurin-17 AS BUILD
COPY Security /home/Security
COPY Security/pom.xml /home/Security
RUN mvn -f /home/Security/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=BUILD home/Security/target/Security-0.0.1-SNAPSHOT.jar security-slp.jar
EXPOSE 8060
ENTRYPOINT ["java", "-jar", "security-slp.jar"]