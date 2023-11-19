FROM maven:3.8.3-eclipse-temurin-17 AS BUILD
COPY . /home
COPY pom.xml /home
RUN mvn -f /home/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=BUILD home/target/SmartLibrarian-1.0.1-SNAPSHOT.jar smart-librarian.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "smart-librarian.jar"]