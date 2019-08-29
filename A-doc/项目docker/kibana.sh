docker run --name lq_kibana -e ELASTICSEARCH_URL="http://60.205.59.223:9200" \
-p 9201:5601 \
-v /opt/elastic/kibana/config:/usr/share/kibana/config \
-v /etc/localtime:/etc/localtime \
-d docker.elastic.co/kibana/kibana:6.5.0