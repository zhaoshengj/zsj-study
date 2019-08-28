docker run -d -p 8200:9200 -p 8300:9300 \
-e "discovery.type=single-node"  \
-v /etc/localtime:/etc/localtime \
--name newes docker.elastic.co/elasticsearch/elasticsearch:6.5.0
