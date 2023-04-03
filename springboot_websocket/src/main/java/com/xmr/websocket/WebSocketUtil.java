package com.xmr.websocket;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xmr on 2019/7/31.
 */
public class WebSocketUtil {
    /**
     * 简单使用map进行存储在线的session
     *
     */
    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();

    public static String addSession(String userNick, Session session) {
        //putIfAbsent 添加键—值对的时候，先判断该键值对是否已经存在
        //不存在：新增，并返回null
        if (!ONLINE_SESSION.containsKey(userNick)){
            ONLINE_SESSION.put(userNick, session);
            return null;
        }
        //存在：不覆盖，直接返回已存在的值
        ONLINE_SESSION.putIfAbsent(userNick, session);
        return userNick;
    }

    public static void removeSession(String userNick) {
        ONLINE_SESSION.remove(userNick);
    }

    /**
     * 向某个用户发送消息
     * @param session 某一用户的session对象
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        if(session == null) {
            return;
        }
        // getAsyncRemote()和getBasicRemote()异步与同步
        RemoteEndpoint.Async async = session.getAsyncRemote();
        //发送消息
        async.sendText(message);
    }

    /**
     * 向所有在线人发送消息
     * @param message
     */
    public static void sendMessageForAll(String message) {
        //jdk8 新方法
        ONLINE_SESSION.forEach((sessionId, session) -> sendMessage(session, message));
    }
}
