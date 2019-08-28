docker run --name kibana -e ELASTICSEARCH_URL="http://120.79.4.13:8200" \
-p 8201:5601 \
-v /opt/data/kibana/config/:/usr/share/kibana/config \
-v /etc/localtime:/etc/localtime \
-d docker.elastic.co/kibana/kibana:7.1.1