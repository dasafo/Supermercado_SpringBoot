FROM openjdk:17-jdk-slim
ARG JAR_FILE=taget/SupermercadoPruebaTecnica-0.0.1.jar
COPY ${JAR_FILE} app_superpruebtec.jar
EXPOSE 8081
LABEL authors="david"

ENTRYPOINT ["java", "-jar", "app_superpruebtec.jar"]