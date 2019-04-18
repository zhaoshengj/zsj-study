echo '停止容器...'
docker stop sentinel
echo '删除容器...'
docker rm sentinel
echo '删除镜像...'
docker rmi sentinel:v1
echo '构建镜像...'
docker build -t sentinel:v1 .
echo '启动容器...'
docker run -d --name sentinel -v /etc/localtime:/etc/localtime -v /opt/zsj/sentiel/logs/:/root/logs/ -p 8849:8080 sentinel:v1
echo '打印日志...'
docker logs -f sentinel