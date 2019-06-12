echo '停止容器...'
docker stop sentinel
echo '删除容器...'
docker rm sentinel
echo '[3/7]删除镜像...'
docker rmi sentinel:v1
echo '[4/7]构建镜像...'
docker build -t sentinel:v1 .
echo '启动容器...'
docker run --name sentinel -d -p 8501:8080 \
-v //opt/data/sentinel/logs://root/logs/ \
-v /etc/localtime:/etc/localtime \
sentinel:v1
echo '打印日志...'
docker logs -f sentinel