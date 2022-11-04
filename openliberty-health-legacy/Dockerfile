FROM icr.io/appcafe/open-liberty:kernel-slim-java11-openj9-ubi

COPY --chown=1001:0 maven/src/main/liberty/config /config

RUN features.sh

COPY --chown=1001:0 maven/target/*.war /config/apps

EXPOSE 9080

RUN configure.sh
