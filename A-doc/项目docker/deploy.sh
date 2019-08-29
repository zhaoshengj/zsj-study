jarFile="QUARTZ-1.0-SNAPSHOT.jar"
if [ -f "$jarFile" ]; then
    echo '[1/7]停止容器...'
    docker stop lq_quartz
    echo '[2/7]删除容器...'
    docker rm lq_quartz
    echo '[3/7]删除镜像...'
    docker rmi lq/quartz:v1
    echo '[4/7]构建镜像...'
    docker build -t lq/quartz:v1 .

    echo '[5/7]备份jar...'
    current=`date "+%Y-%m-%d %H:%M:%S"`
    timeStamp=`date -d "$current" +%s`
    # 将current转换为时间戳，精确到毫秒
    currentTimeStamp=$((timeStamp*1000+`date "+%N"`/1000000))
    mv $jarFile backup/QUARTZ_BACK_$currentTimeStamp.jar

    echo '[6/7]启动容器...'
    docker run -e JAVA_OPTS='-Xms256m -Xmx512m' -d --name lq_quartz -p 7003:7003 -p 50031:50031 -v /lqnas/quartz/:/opt/app/quartz/ --net=host  lq/quartz:v1
    echo '[7/7]打印日志...'
    docker logs -f lq_quartz
else
    echo 'ERROR:启动失败，当前目录下Jar包不存在...'
fi
