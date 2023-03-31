FROM openjdk:20-jdk-slim
VOLUME /tmp
WORKDIR /app
COPY ./target/*.jar /app/service-login.jar
EXPOSE 20000
ENTRYPOINT ["java","-jar","service-login.jar"]