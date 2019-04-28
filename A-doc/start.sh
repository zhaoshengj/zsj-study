echo '停止容器...'
docker stop nginx
echo '删除容器...'
docker rm nginx
echo '启动容器...'
docker run --name nginx -d -p 80:80  -p 443:443 -v /opt/data/nginx/html:/usr/share/nginx/html \
-v /opt/data/nginx/conf:/etc/nginx/conf \
-v /opt/data/nginx/conf.d/default.conf:/etc/nginx/conf.d/default.conf \
-v /opt/data/nginx/logs:/var/log/nginx \
-v /opt/static:/opt/static \
-v /etc/localtime:/etc/localtime \
nginx-web
echo '打印日志...'
docker logs -f nginx