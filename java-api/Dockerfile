FROM eclipse-temurin:21-jdk-alpine

WORKDIR /usr/src/app

RUN wget -O postgresql.jar https://jdbc.postgresql.org/download/postgresql-42.7.4.jar

COPY Main.java .

RUN javac -cp postgresql.jar Main.java

EXPOSE 8080

CMD ["java", "-cp", ".:postgresql.jar", "Main"]