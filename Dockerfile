FROM openjdk:8-jre
RUN apt-get update -y && apt-get install -y tzdata
RUN cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
ADD target/TransApp*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=America/Sao_Paulo", "-jar", "/app.jar"]
EXPOSE 8080
