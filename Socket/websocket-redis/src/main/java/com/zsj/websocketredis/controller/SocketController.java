package com.zsj.websocketredis.controller;

import com.zsj.websocketredis.common.WebSocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author zsj
 * @date 2019-04-24  12:31
 */
@Component
@ServerEndpoint(value = "/connect/{identifier}")
public class SocketController extends WebSocketEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    /// 无法通过这种方式注入组件
    @Autowired
    private WebSocketManager websocketManager;

    @OnOpen
    public void onOpen(Session session, @PathParam(IDENTIFIER) String identifier) {
        System.out.println("连接成功");
        logger.info("*** WebSocket opened from sessionId " + session.getId() + " , identifier = " + identifier);
        connect(identifier, session);
    }

    @OnMessage
    public void onMessage(String message, Session session , @PathParam(IDENTIFIER) String identifier) {
        logger.info("接收到的数据为：" + message + " from sessionId " + session.getId() + " , identifier = " + identifier);
        receiveMessage(identifier, message, session);
    }

    @OnClose
    public void onClose(Session session , @PathParam(IDENTIFIER) String identifier) {
        logger.info("*** WebSocket closed from sessionId " + session.getId() + " , identifier = " + identifier);
        disconnect(identifier);
    }

    @OnError
    public void onError(Throwable t , @PathParam(IDENTIFIER) String identifier){
        logger.info("发生异常：, identifier = " + identifier);
        logger.error(t.getMessage() , t);
        disconnect(identifier);
    }

}
