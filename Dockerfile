FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS="-DLOG_HOME=/logs/"
EXPOSE 8080
ENTRYPOINT ["java","-DLOG_HOME=/logs/","-jar","/app.jar"]