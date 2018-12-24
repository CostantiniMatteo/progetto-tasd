FROM maven:3.6-jdk-8-alpine as build
COPY . /home/jobcenter
WORKDIR /home/jobcenter
VOLUME /Users/lorenzodivito/.m2:/root/.m2
RUN mvn clean install -DskipTests

FROM openjdk:8-jre-alpine
COPY --from=build /home/jobcenter/target/jobcenter-0.0.1-SNAPSHOT.jar /app/jobcenter.jar
EXPOSE 8200
ENTRYPOINT ["/usr/bin/java", "-jar", "/app/jobcenter.jar"]