package com.vren.weldingmonitoring_java.socket.server;

import com.alibaba.fastjson.JSONObject;
import com.vren.weldingmonitoring_java.wave.socket.server.SocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.concurrent.*;

/**
 * @ClassName:SocketListener
 * @Author: vren
 * @Date: 2022/8/1 17:50
 */
@Component
@Slf4j
public class SocketListener extends SocketServer {

    private String images;

    private static final ExecutorService executorService = new ThreadPoolExecutor(300, 500, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

    public String getImages() {
        return images;
    }

    @Override
    public void onClientDisconnect(Socket socket) {
        images = null;
    }

    @Override
    public void onMessage(Socket socket, String message) {
        ImageBody imageBody = JSONObject.parseObject(message, ImageBody.class);
        images = imageBody.getPicture();
        NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
        noticeWebsocketResp.setNoticeType("IMAGE");
        noticeWebsocketResp.setNoticeInfo(imageBody);
        executorService.execute(() -> WebsocketServer.sendMessage(noticeWebsocketResp));
    }

    @Override
    public Integer getPort() {
        return 7777;
    }
}
