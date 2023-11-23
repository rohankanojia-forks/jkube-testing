mvn k8s:resource k8s:helm
sh ~/work/scripts/startDockerRegistryLocal.sh
mvn k8s:helm-push -Pdockerhub
mvn k8s:helm-push -Pquay
mvn k8s:helm-push -Pghcr
mvn k8s:helm-push -Plocal-docker
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
