FROM openjdk:11-jre-slim
MAINTAINER federico_and_ioan.com
COPY target/ProgettoIng-0.0.1-SNAPSHOT.jar ProgettoIng-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/ProgettoIng-0.0.1-SNAPSHOT.jar"]