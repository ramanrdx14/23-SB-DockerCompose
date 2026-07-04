FROM eclipse-temurin:17-jdk
MAINTAINER "<Ramandeep55>"
EXPOSE 8080
COPY target/23-SB-DockerCompose-0.0.1-SNAPSHOT.jar /user/app
WORKDIR /user/app
ENTRYPOINT ["java", "-jar","23-SB-DockerCompose-0.0.1-SNAPSHOT.jar"]