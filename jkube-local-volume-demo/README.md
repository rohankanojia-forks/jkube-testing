# jkube-local-volume-demo Project

## Overview
This is a demo application showcasing how to use Kubernetes Local volume with Kubernetes Maven Plugin.

This application exposes one endpoint:
- `POST` `/client/multipart` recieves MultiPartBody in request body.

User uploads file via this endpoint to `/deployments/uploads` folder. This folder is also mounted as volume which uses a PersistentVolumeClaim to request volume from `minikube-pv` volume we created earlier. Any changes to this directory would be reflected in node's `/mnt/vda1/data` directory (see `pom.xml`).

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
$ kubectl get pv
NAME              CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS   CLAIM                    STORAGECLASS    REASON   AGE
minikube-pv       50Mi       RWO            Delete           Bound    default/task-pv-claim    local-storage            2s
$ jkube-local-volume-demo : $ kubectl get pods
NAME                                          READY   STATUS    RESTARTS      AGE
jkube-local-volume-demo-6bb9c59f7c-9v92z   1/1     Running   0             48s
```
Check application logs
```shell
$ mvn k8s:log
```

In separate terminal window, try hitting application url:
```shell
$ minikube service jkube-local-volume-demo --url
http://192.168.39.214:32666
$ curl -v -F fileName=first.txt -F file=@/home/rokumar/Downloads/first.txt 192.168.39.214:32666/client/multipart
*   Trying 192.168.39.214:32666...
* Connected to 192.168.39.214 (192.168.39.214) port 32666 (#0)
> POST /client/multipart HTTP/1.1
> Host: 192.168.39.214:32666
> User-Agent: curl/7.85.0
> Accept: */*
> Content-Length: 297
> Content-Type: multipart/form-data; boundary=------------------------7f1c5e5b44304b79
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
~ : $ minikube ssh
                         _             _            
            _         _ ( )           ( )           
  ___ ___  (_)  ___  (_)| |/')  _   _ | |_      __  
/' _ ` _ `\| |/' _ `\| || , <  ( ) ( )| '_`\  /'__`\
| ( ) ( ) || || ( ) || || |\`\ | (_) || |_) )(  ___/
(_) (_) (_)(_)(_) (_)(_)(_) (_)`\___/'(_,__/'`\____)

$ cd /mnt/vda1/data/
$ ls
first.txt
$ cat first.txt 
roa
```
