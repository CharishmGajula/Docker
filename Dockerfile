FROM openjdk:17-jdk-slim
WORKDIR /app
COPY FactorialServer.java .
RUN javac FactorialServer.java
EXPOSE 8080
CMD ["java", "FactorialServer"]
