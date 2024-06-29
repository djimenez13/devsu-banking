FROM openjdk:21

LABEL version="0.0.1"
LABEL description="Customer service of Devsu Banking System"
LABEL vendor="David Jimenez"
LABEL maintainer="jimenez.vasquez.david@gmail.com"

ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

VOLUME /devsu

ADD ./customer-svc-0.0.1-SNAPSHOT.jar customer-svc.jar

ENTRYPOINT ["java", "-jar", "/customer-svc.jar"]