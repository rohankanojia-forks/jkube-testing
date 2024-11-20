# jkube-hostpath-volume-demo Project

## Overview
This is a demo application showcasing how to use Kubernetes HostPath volume with Kubernetes Maven Plugin.

This application exposes one endpoint:
- `POST` `/client/multipart` recieves MultiPartBody in request body.

User uploads file via this endpoint to `/deployments/uploads` folder. This folder is also mounted as hostpath volume
so any changes to this directory would be reflected in node's `/data` directory (see `pom.xml`).

You can run application locally using this command:
```bash
$ mvn quarkus:dev
```

## Deploying to Kubernetes
I used minikube as my underlying Kubernetes cluster. Run these commands to deploy application to minikube:
```shell
$ eval $(minikube -p minikube docker-env)
$ mvn k8s:build k8s:resource k8s:apply
```
Once this command finishes, check deployed application
```shell
$ jkube-hostpath-volume-demo : $ kubectl get pods
NAME                                          READY   STATUS    RESTARTS      AGE
jkube-hostpath-volume-demo-6bb9c59f7c-9v92z   1/1     Running   0             48s
```
Check application logs
```shell
$ mvn k8s:log
```

In separate terminal window, try hitting application url:
```shell
$ SERVER_URL=`minikube service jkube-hostpath-volume-demo --url`
http://192.168.39.214:30948
$ curl -v -F fileName=first.txt -F file=@/home/rokumar/Downloads/first.txt $SERVER_URL/client/multipart
*   Trying 192.168.39.214:30948...
* Connected to 192.168.39.214 (192.168.39.214) port 30948 (#0)
> POST /client/multipart HTTP/1.1
> Host: 192.168.39.214:30948
> User-Agent: curl/7.85.0
> Accept: */*
> Content-Length: 278
> Content-Type: multipart/form-data; boundary=------------------------9604dcced8211447
> 
* We are completely uploaded and fine
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< content-length: 0
< 
* Connection #0 to host 192.168.39.214 left intact
```

In separate window, SSH into minikube and check whether uploaded file is present in minikube's node:
```shell
$ minikube ssh
                         _             _            
            _         _ ( )           ( )           
  ___ ___  (_)  ___  (_)| |/')  _   _ | |_      __  
/' _ ` _ `\| |/' _ `\| || , <  ( ) ( )| '_`\  /'__`\
| ( ) ( ) || || ( ) || || |\`\ | (_) || |_) )(  ___/
(_) (_) (_)(_)(_) (_)(_)(_) (_)`\___/'(_,__/'`\____)

$ cat /data/first.txt 
roa
```
