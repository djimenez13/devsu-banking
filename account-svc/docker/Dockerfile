FROM openjdk:21

LABEL version="0.0.1"
LABEL description="Account service of Devsu Banking System"
LABEL vendor="David Jimenez"
LABEL maintainer="jimenez.vasquez.david@gmail.com"

ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

VOLUME /devsu

ADD ./account-svc-0.0.1-SNAPSHOT.jar account-svc.jar

ENTRYPOINT ["java", "-jar", "/account-svc.jar"]