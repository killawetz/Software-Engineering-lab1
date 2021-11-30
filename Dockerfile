FROM openjdk:8-jdk-alpine
COPY /build/libs/Software-Engineering-lab1-1.0-SNAPSHOT.jar /converter.jar
CMD ["java", "-jar", "/converter.jar"]