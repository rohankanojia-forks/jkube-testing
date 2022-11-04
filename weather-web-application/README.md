# Eclipse JKube Simple Weather Web Application Quickstart

This quickstart demonstrates the use of CDI and Servlet 3 and is a good starting point to see how you can create a simple web application.

## How to Build:
Just run `mvn clean install` to build project

## How to Containerize this application:
We will be using Eclipse JKube to build a docker image of this application. To build docker image against a docker daemon( If you're using minikube you can expose it using `minikube docker-env` command), just run `mvn k8s:build` goal:

```
mvn k8s:build
```

## Generating Kubernetes Artifacts and Deploying to Kubernetes
We will be using Eclipse JKube to do this. Just need to run resource and deploy goals:
```
mvn k8s:resource k8s:deploy
```

