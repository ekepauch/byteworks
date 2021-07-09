FROM openjdk:8-jdk-alpine
MAINTAINER Ekenedirichukwu Amaechi
COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]

