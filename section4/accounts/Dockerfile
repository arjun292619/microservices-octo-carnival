#base image for java runtime
FROM openjdk:25-slim

LABEL "org.opencontainers.image.authors" = "jcar@email.com"

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "accounts-0.0.1-SNAPSHOT.jar"]