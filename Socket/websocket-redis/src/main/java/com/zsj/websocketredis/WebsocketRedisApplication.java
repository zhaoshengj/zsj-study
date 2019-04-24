package com.zsj.websocketredis;

import com.zsj.websocketredis.common.WebSocketManager;
import com.zsj.websocketredis.redis.EnableRedisWebSocketManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableRedisWebSocketManager
public class WebsocketRedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WebsocketRedisApplication.class, args);

        Object bean = applicationContext.getBean(WebSocketManager.WEBSOCKET_MANAGER_NAME);

        System.out.println(bean);
    }

}
