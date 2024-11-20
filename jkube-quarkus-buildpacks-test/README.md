# jkube-quarkus-buildpacks-test

This is a demo project using Eclipse JKube to use demonstrate `buildpacks` build strategy.

We well be using quarkus's builder, in order to override default buildpack builder image used by Eclipse JKube, change it in pack config
```shell
$ pack config default-builder codejive/buildpacks-quarkus-builder:jvm
```
Then run `mvn k8s:build` to create container image:
```
$ mvn k8s:build
```
