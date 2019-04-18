FROM java:8-jdk-alpine

MAINTAINER zhaosj_vip@163.com

COPY sentinel-dashboard-1.5.1.jar sentinel.jar

EXPOSE 8849

ENV TZ=Asia/Shanghai

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /root/logs/

VOLUME /opt/zsj/sentiel/logs/

ENTRYPOINT exec java $JAVA_OPTS -jar /sentinel.jar
