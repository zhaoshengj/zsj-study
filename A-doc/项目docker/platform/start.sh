echo '停止容器...'
docker stop platform
echo '删除容器...'
docker rm platform
echo '启动容器...'
docker run --name platform -d -p 8100:8080 \
-v /opt/zsj/platform/webapps:/usr/local/tomcat/webapps \
-v /opt/zsj/platform/logs:/opt/zsj/platform/logs \
-v /etc/localtime:/etc/localtime \
docker.io/tomcat
echo '打印日志...'
docker logs -f platform