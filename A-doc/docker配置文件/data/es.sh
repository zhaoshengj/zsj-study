docker run -d -p 8200:9200 -p 8300:9300 \
-e ES_JAVA_OPTS="-Xms128m -Xmx256m" \
-e "discovery.type=single-node"  \
-v /opt/data/es/data:/usr/share/elasticsearch/data \
-v /opt/data/es/plugins:/usr/share/elasticsearch/plugins \
-v /etc/localtime:/etc/localtime \
--name es docker.elastic.co/elasticsearch/elasticsearch:7.1.1
