# MicroProfile generated Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you containing some endpoints which are called from the main application (see the `service-a` directory)

The generation of the executable jar file can be performed by issuing the following command



    ./gradlew microBundle



This will create an executable jar file **payara-micro-demo-gradle-microbundle.jar** within the _build/libs_ gradle folder. This can be started by executing the following command

    ./gradlew microStart





## Specification examples




### Rest Client

A type safe invocation of HTTP rest endpoints. Specification [here](https://microprofile.io/project/eclipse/microprofile-rest-client)

The example calls one endpoint from another JAX-RS resource where generated Rest Client is injected as CDI bean.
