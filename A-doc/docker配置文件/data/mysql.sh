echo '停止容器...'
docker stop mysql
echo '删除容器...'
docker rm mysql
echo '启动容器...'
docker run --name mysql -d -p 3306:3306 --privileged=true \
-v /opt/data/mysql/conf/my.cnf:/etc/mysql/my.cnf \
-v /opt/data/mysql/data:/var/lib/mysql \
-v /etc/localtime:/etc/localtime \
-e MYSQL_ROOT_PASSWORD=admin123 mysql:5.7
echo '打印日志...'
docker logs -f mysql