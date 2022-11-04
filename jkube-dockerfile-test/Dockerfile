ARG VERSION=8u272-jdk
FROM openjdk:$VERSION
COPY maven/docker-file-simple.jar /home/docker-file-simple.jar
ENTRYPOINT ["java", "-jar", "/home/docker-file-simple.jar"]
