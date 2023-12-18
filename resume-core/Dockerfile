FROM  khipu/openjdk17-alpine
VOLUME /tmp
ARG JAR_FILE
ADD target/resume-core-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "/app.jar"]