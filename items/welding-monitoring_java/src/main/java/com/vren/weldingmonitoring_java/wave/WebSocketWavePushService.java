package com.vren.weldingmonitoring_java.wave;

import com.vren.weldingmonitoring_java.socket.server.DeviceForShow;
import com.vren.weldingmonitoring_java.socket.server.NoticeWebsocketResp;
import com.vren.weldingmonitoring_java.socket.server.WebsocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class WebSocketWavePushService {

    private final List<DeviceForShow> list = new ArrayList<>();

    @Autowired
    private QualityEvaluationService qualityEvaluationService;


    public void setListData(DeviceForShow data) {
        synchronized (list) {
            list.add(data);
        }
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void run() {
        List<DeviceForShow> temp;
        synchronized (list) {
            temp = new ArrayList<>(list);
            list.clear();
        }
        if (temp.size() > 0) {
            double i = 0, u = 0;
            for (DeviceForShow deviceForShow : temp) {
                i += deviceForShow.getI();
                u += deviceForShow.getU();
            }
            DeviceForShow deviceForShow = new DeviceForShow();
            deviceForShow.setI(i / temp.size());
            deviceForShow.setU(u / temp.size());
            deviceForShow.setTime(new Date());
            if (qualityEvaluationService.isStart()) {
                qualityEvaluationService.setForShowList(deviceForShow);
            }
            NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
            noticeWebsocketResp.setNoticeType("CURRENTVOLTAGE");
            noticeWebsocketResp.setNoticeInfo(deviceForShow);
            WebsocketServer.sendMessage(noticeWebsocketResp);
        }
    }
}
