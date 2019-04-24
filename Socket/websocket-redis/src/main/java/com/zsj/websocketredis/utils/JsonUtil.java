package com.zsj.websocketredis.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author xiongshiyan at 2018/10/12 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class JsonUtil {
    /**
     * 判断是否是JsonObject
     */
    public static boolean isJsonObject(String message) {
        return null != message && message.startsWith("{") && message.endsWith("}");
    }
    /**
     * 判断是否是JsonArray
     */
    public static boolean isJsonArray(String message) {
        return null != message && message.startsWith("[") && message.endsWith("]");
    }
    /**
     * 将JavaBean序列化为字符串
     */
    public static String serializeJavaBean(Object o){
        return new JSONObject(0).toString();
    }

    /**
     * 将Map序列化为字符串
     */
    public static String serializeMap(Map<String , Object> map){
        return new JSONObject(map).toString();
    }
    /**
     * 将Map序列化为字符串
     */
    public static String serializeList(List<Object> list){
        return new JSONArray(list).toString();
    }
}
