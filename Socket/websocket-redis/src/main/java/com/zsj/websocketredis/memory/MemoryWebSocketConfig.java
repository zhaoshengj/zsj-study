package com.zsj.websocketredis.memory;

import com.zsj.websocketredis.WebSocketManager;
import com.zsj.websocketredis.config.WebSocketHeartBeatChecker;
import com.zsj.websocketredis.utils.SpringContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiongshiyan
 * 内存管理websocket配置
 */
@Configuration
public class MemoryWebSocketConfig {
    /**
     * applicationContext全局保存器
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }

    @Bean(WebSocketManager.WEBSOCKET_MANAGER_NAME)
    @ConditionalOnMissingBean(name = WebSocketManager.WEBSOCKET_MANAGER_NAME)
    public WebSocketManager webSocketManager() {
        return (WebSocketManager) new MemWebSocketManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketHeartBeatChecker webSocketHeartBeatChecker(){
        return new WebSocketHeartBeatChecker();
    }
}