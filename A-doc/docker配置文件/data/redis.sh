echo '停止容器...'
docker stop redis
echo '删除容器...'
docker rm redis
echo '启动容器...'
docker run -d --name redis -p 6379:6379 \
-v /opt/data/redis/data:/data \
-v /etc/localtime:/etc/localtime \
docker.io/redis --requirepass "admin123" --appendonly yes
echo '打印日志...'
docker logs -f redis