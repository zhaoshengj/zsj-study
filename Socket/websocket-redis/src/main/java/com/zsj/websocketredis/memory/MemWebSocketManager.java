package com.zsj.websocketredis.memory;

import com.zsj.websocketredis.pojo.WebSocket;
import com.zsj.websocketredis.common.WebSocketCloseEvent;
import com.zsj.websocketredis.common.WebSocketConnectEvent;
import com.zsj.websocketredis.common.WebSocketManager;
import com.zsj.websocketredis.utils.SpringContextHolder;
import com.zsj.websocketredis.utils.WebSocketUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiongshiyan at 2018/10/10 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class MemWebSocketManager implements WebSocketManager {
    /**
     * 因为全局只有一个 WebSocketManager ，所以才敢定义为非static
     */
    private final Map<String, WebSocket> connections = new ConcurrentHashMap<>();

    @Override
    public WebSocket get(String identifier) {
        return connections.get(identifier);
    }

    @Override
    public void put(String identifier, WebSocket webSocket) {
        connections.put(identifier , webSocket);
        //发送连接事件
        SpringContextHolder.getApplicationContext().publishEvent(new WebSocketConnectEvent(identifier));
    }

    @Override
    public void remove(String identifier) {
        connections.remove(identifier);
        //发送关闭事件
        SpringContextHolder.getApplicationContext().publishEvent(new WebSocketCloseEvent(identifier));
    }


    @Override
    public Map<String, WebSocket> localWebSocketMap() {
        return connections;
    }

    @Override
    public void sendMessage(String identifier, String message) {
        WebSocket webSocket = get(identifier);
        if(null == webSocket){throw new RuntimeException("identifier 不存在");}

        WebSocketUtil.sendMessage(webSocket.getSession() , message);
    }

    @Override
    public void broadcast(String message) {
        localWebSocketMap().values().forEach(
                webSocket -> WebSocketUtil.sendMessage(
                        webSocket.getSession() , message));
    }

    @Override
    public void onMessage(String identifier, String message) {

        sendMessage(identifier,message);

    }
}
