FROM eclipse-temurin:17-jdk
MAINTAINER "<Ramandeep55>"
WORKDIR /user/app
COPY target/23-SB-DockerCompose-0.0.1-SNAPSHOT.jar /user/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar","23-SB-DockerCompose-0.0.1-SNAPSHOT.jar"]