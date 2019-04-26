package com.zsj.websocketredis.redis.action;


import com.alibaba.fastjson.JSONObject;
import com.zsj.websocketredis.common.WebSocket;
import com.zsj.websocketredis.common.WebSocketManager;
import com.zsj.websocketredis.utils.WebSocketUtil;

/**
 * {
 *     "action":"sendMessage",
 *     "identifier":"xxx",
 *     "message":"xxxxxxxxxxx"
 * }
 * 给webSocket发送消息的action
 * @author xiongshiyan at 2018/10/12 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class SendMessageAction implements Action{
    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        if(!object.containsKey(IDENTIFIER)){
            return;
        }
        if(!object.containsKey(MESSAGE)){
            return;
        }

        String identifier = object.getString(IDENTIFIER);

        WebSocket webSocket = manager.get(identifier);
        if(null == webSocket){
            return;
            //System.out.println(identifier);
        }
        //new BroadCastAction().doMessage(manager,object);
        WebSocketUtil.sendMessage(webSocket.getSession() , object.getString(MESSAGE));
    }
}
