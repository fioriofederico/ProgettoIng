FROM openjdk:8-jdk-alpine
MAINTAINER federico_and_ioan.com
COPY target/ProgettoIng-0.0.1-SNAPSHOT.jar ProgettoIng-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ProgettoIng-0.0.1-SNAPSHOT.jar"]