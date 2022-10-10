FROM openjdk:8-jre
MAINTAINER Raja Sivagurunathan <sivagururaja@gmail.com>
COPY target/lib/*.jar /lib/
COPY target/stay-active-1.0-SNAPSHOT.jar stay-active.jar
ENTRYPOINT ["java", "-jar", "/stay-active.jar"]
