FROM openjdk:8-jdk

COPY . /app

WORKDIR /app

RUN ./gradlew -v

EXPOSE 3333
EXPOSE 5005

CMD ./gradlew -t build & ./gradlew runDebug