docker run -d -p 9200:9200 -p 9300:9300 \
-e ES_JAVA_OPTS="-Xms256m -Xmx512m" \
-e "discovery.type=single-node"  \
-v /opt/elastic/elastic/data:/usr/share/elasticsearch/data \
-v /opt/elastic/elastic/plugins:/usr/share/elasticsearch/plugins \
-v /etc/localtime:/etc/localtime \
--name lq_es docker.elastic.co/elasticsearch/elasticsearch:6.5.0