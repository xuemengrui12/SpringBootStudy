package com.xmr.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 由于是websocket原本是@RestController的http形式
 * 直接替换成@ServerEndpoint即可，作用是一样的 就是指定一个地址
 * 表示定义一个websocket的Server端
 * Created by xmr on 2019/7/31.
 */
@Component
@ServerEndpoint(value = "/my-chat/{usernick}")
//@Slf4j
public class WebSocketController {
    /**
     * 连接事件 加入注解
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "usernick") String userNick, Session session) {
        String result = WebSocketUtil.addSession(userNick, session);
        if (result != null) {
            String message = "有新游客[" + userNick + "]加入聊天室!";
            System.out.println(message);
            //此时可向所有的在线通知 某某某登录了聊天室
            WebSocketUtil.sendMessageForAll(message);
        }
    }

    @OnClose
    public void onClose(@PathParam(value = "usernick") String userNick, Session session) {
        String message = "游客[" + userNick + "]退出聊天室!";
        System.out.println(message);
        WebSocketUtil.removeSession(userNick);
        //此时可向所有的在线通知 某某某登录了聊天室
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnMessage
    public void OnMessage(@PathParam(value = "usernick") String userNick, String message) {
        //类似群发
        String info = "游客[" + userNick + "]：" + message;
        System.out.println(info);
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("异常:" + throwable);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }
}
