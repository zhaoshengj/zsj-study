package com.zsj.websocketredis;

/**
 * @author xiongshiyan at 2019/3/20 , contact me with email yanshixiong@126.com or phone 15208384257
 */
@FunctionalInterface
public interface TodoAtRemove {
    /**
     * 在删除的时候额外要干什么
     * @param webSocket  webSocket
     */
    void todoWith(WebSocket webSocket);
}
