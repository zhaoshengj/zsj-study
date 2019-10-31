docker run --detach --name solo --network=host \
--env RUNTIME_DB="MYSQL" \
--env JDBC_USERNAME="root" \
--env JDBC_PASSWORD="admin123" \
--env JDBC_DRIVER="com.mysql.cj.jdbc.Driver" \
--env JDBC_URL="jdbc:mysql://120.79.4.13:3306/solo?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC" \
-v /opt/zsj/solo/skins:/opt/solo/skins/ \
--rm \
b3log/solo --listen_port=8080 --server_scheme=http --server_host=guojiaqi.zhaosj.cn