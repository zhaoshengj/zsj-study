echo '停止容器...'
docker stop nacos
echo '删除容器...'
docker rm nacos
echo '启动容器...'
docker run --env MODE=standalone --name nacos -d -p 8500:8848 \
-v /opt/data/nacos/nacos/conf:/home/nacos/conf \
-v /opt/data/nacos/nacos/logs:/home/nacos/logs \
-v /etc/localtime:/etc/localtime \
nacos/nacos-server
echo '打印日志...'
docker logs -f nacos