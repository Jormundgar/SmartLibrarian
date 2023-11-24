FROM maven:3.8.3-eclipse-temurin-17 AS BUILD
COPY APIGateway /home/Backend
COPY APIGateway/pom.xml /home/APIGateway
RUN mvn -f /home/APIGateway/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=BUILD home/APIGateway/target/APIGateway-0.0.1-SNAPSHOT.jar apigateway-slp.jar
EXPOSE 8050
ENTRYPOINT ["java", "-jar", "apigateway-slp.jar"]