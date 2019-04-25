jarFile="GOANSWER-1.0-SNAPSHOT.jar"
if [ -f "$jarFile" ]; then
    echo '[1/7]停止容器...'
    docker stop lq_goanswer
    echo '[2/7]删除容器...'
    docker rm lq_goanswer
    echo '[3/7]删除镜像...'
    docker rmi lq/goanswer:v1
    echo '[4/7]构建镜像...'
    docker build -t lq/goanswer:v1 .

    echo '[5/7]备份jar...'
    current=`date "+%Y-%m-%d %H:%M:%S"`
    timeStamp=`date -d "$current" +%s`
    # 将current转换为时间戳，精确到毫秒
    currentTimeStamp=$((timeStamp*1000+`date "+%N"`/1000000))
    mv $jarFile backup/GOANSWER_BACK_$currentTimeStamp.jar

    echo '[6/7]启动容器...'
    docker run -e JAVA_OPTS='-Xms256m -Xmx512m' -d --name lq_goanswer -p 7009:7009 -p 50091:50091 -v /opt/app/goanswer/:/opt/app/goanswer/ --net=host  lq/goanswer:v1
    echo '[7/7]打印日志...'
    docker logs -f lq_goanswer
else
    echo 'ERROR:启动失败，当前目录下Jar包不存在...'
fi
