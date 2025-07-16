FROM openjdk:17-jdk

RUN mkdir /container

WORKDIR /container

VOLUME /container

ARG JAR_FILE=build/libs/container-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} container.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "container.jar"]