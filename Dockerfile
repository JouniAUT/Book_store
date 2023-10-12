FROM maven:3.8.6-eclipse-temurin-17-focal AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM eclipse-temurin:17-jre-focal
COPY --from=build back_end_syksy_2023\sof03_mavenprojektit\kirjakauppaa-0.0.1-SNAPSHOT.jar /usr/local/lib/kirjakauppajt25.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/kirjakauppajt25.jar"]