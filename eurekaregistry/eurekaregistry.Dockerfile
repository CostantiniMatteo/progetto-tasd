FROM maven:3.6-jdk-8-alpine as build
COPY . /home/eurekaregistry
WORKDIR /home/eurekaregistry
VOLUME /Users/lorenzodivito/.m2:/root/.m2
RUN mvn clean install -DskipTests

FROM openjdk:8-jre-alpine
COPY --from=build /home/eurekaregistry/target/eurekaregistry-0.0.1-SNAPSHOT.jar /app/eurekaregistry.jar
EXPOSE 8760-8770
ENTRYPOINT ["/usr/bin/java", "-jar", "/app/eurekaregistry.jar"]