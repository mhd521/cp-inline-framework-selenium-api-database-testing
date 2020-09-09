FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

#Workspace
WORKDIR /cpqadocker

ADD cp.jar                     cp.jar
ADD cp-tests.jar               cp-tests.jar
ADD libs/              	       libs/
ADD ScreenShots/               ScreenShots/
ADD Reports/extent.html        Reports/extent.html
ADD Smoke.xml                  Smoke.xml
ADD config.properties          config.properties
ADD log4j.properties           log4j.properties

ADD healthcheck.sh             healthcheck.sh

ENTRYPOINT  sh healthcheck.sh