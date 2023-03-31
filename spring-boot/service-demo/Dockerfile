FROM openjdk:20-jdk-slim
VOLUME /tmp
WORKDIR /app
COPY ./target/*.jar /app/service-demo.jar
EXPOSE 20001
ENTRYPOINT ["java","-jar","service-demo.jar"]