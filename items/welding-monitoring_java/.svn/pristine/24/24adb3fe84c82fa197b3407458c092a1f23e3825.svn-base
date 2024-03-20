package com.vren.weldingmonitoring_java.wave;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.vren.weldingmonitoring_java.config.SystemConfig;
import com.vren.weldingmonitoring_java.wave.domain.dto.NoticeMessage;
import com.vren.weldingmonitoring_java.wave.socket.client.SocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ImagePushService extends SocketClient {

    private final BlockingQueue<NoticeMessage> queue = new LinkedBlockingQueue<>();

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private QualityEvaluationService qualityEvaluationService;


    @Override
    public String getHost() {
        return systemConfig.getImagePushHost();
    }

    @Override
    public Integer getPort() {
        return systemConfig.getImagePushPort();
    }

    @Override
    public void onMessage(Object message) {

    }

    public void push(NoticeMessage noticeMessage) {
        if (getSocket() == null) {
            return;
        }
        try {
            this.queue.put(noticeMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 每一秒每种错误只能发送一次
     */
    private final static ConcurrentHashMap<Integer, Long> timeLimit = new ConcurrentHashMap<>() {{
        put(1, 0L);
        put(2, 0L);
        put(3, 0L);
        put(4, 0L);
        put(5, 0L);
    }};

    @Override
    public boolean onConnect() {
        new Thread(() -> {
            while (true) {
                try {
                    long now = System.currentTimeMillis() / 1000;
                    NoticeMessage take = this.queue.poll(1, TimeUnit.SECONDS);
                    if (take == null || now == timeLimit.get(take.getType())) {
                        continue;
                    }
                    qualityEvaluationService.setErrors(new Date(), take.getType());
                    String s = JSONObject.toJSONString(take, SerializerFeature.WriteMapNullValue);
                    writeLine(s);
                    timeLimit.put(take.getType(), now);
                } catch (Exception ignored) {
                }
            }
        }).start();
        return false;
    }


}
