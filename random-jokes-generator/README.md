# Random Jokes Generator 

This is a simple Quarkus Application that generates random jokes on the root endpoint. It fetches jokes randomly from two existing services running already in Kubernetes Cluster:
- Consume jokes from Chuck Norris service
- Consume jokes from Jokes service
- Endpoints:
  - HTTP Get `/random-joke`
    Returns random joke from any of the scoped services


In order to use it, you must install the dependent services in your Kubernetes Cluster:

```shell
$ kubectl create -f chuck-norris.yml
$ kubectl create -f jokes.yml
```

You can then use `mvn oc:remote-dev` goal to expose these applications on your local machine for inner loop development.
