package com.vren.weldingmonitoring_java.socket.server;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author GR
 * time 2023-07-03-13-12
 **/
@ServerEndpoint("/notice/{userId}")
@Component
@Slf4j
public class WebsocketServer {

    //记录连接的客户端
    private static CopyOnWriteArraySet<WebsocketServer> webSockets = new CopyOnWriteArraySet<>();

    private Session session;


    public static Map<String, Set<String>> conns = new ConcurrentHashMap<>();


    private String sid = null;

    private String userId;


    @OnMessage
    public void onMessage(String message) {
        log.info("收到客户端{}消息:{}", session.getId(), message);
        try {
            session.getBasicRemote().sendText("知道了");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        String tempSid = session.getId();
        this.sid = tempSid;
        this.userId = userId;
        webSockets.add(this);
        this.session = session;
        Set<String> clientSet = conns.get(userId);
        if (clientSet == null) {
            clientSet = new HashSet<>();
            conns.put(userId, clientSet);
        }
        clientSet.add(tempSid);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        webSockets.remove(this);
    }

    /**
     * 发送给所有用户
     *
     * @param noticeWebsocketResp
     */
    public static void sendMessage(NoticeWebsocketResp noticeWebsocketResp) {
        String message = JSONObject.toJSONString(noticeWebsocketResp);
        for (WebsocketServer websocketServer : webSockets) {
            synchronized (websocketServer.session) {
                try {
                    if (websocketServer.session.isOpen()) {
                        websocketServer.session.getBasicRemote().sendText(message);
                    }
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
            }
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }


}
