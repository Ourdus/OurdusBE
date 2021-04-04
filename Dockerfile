FROM openjdk:11-jre-slim
EXPOSE 8080
ARG JAR_FILES=ourdus-spring/build/libs/*.jar
COPY ${JAR_FILES} Ourdus-springboot.jar
ENTRYPOINT [ "java", "-Djava.security.egd=ee:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "Ourdus-springboot.jar" ]