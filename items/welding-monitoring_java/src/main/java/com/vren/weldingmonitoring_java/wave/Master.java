package com.vren.weldingmonitoring_java.wave;

import com.vren.weldingmonitoring_java.config.SystemConfig;
import com.vren.weldingmonitoring_java.domain.entity.Penetration;
import com.vren.weldingmonitoring_java.domain.entity.WeldingTasks;
import com.vren.weldingmonitoring_java.socket.server.DeviceForShow;
import com.vren.weldingmonitoring_java.wave.domain.dto.NoticeMessage;
import com.vren.weldingmonitoring_java.wave.domain.entity.MasterWave;
import com.vren.weldingmonitoring_java.wave.service.WaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class Master implements QueueTask {

    private String masterId;

    private Date startTime;

    private boolean save;

    @Autowired
    private QualityEvaluationService qualityEvaluationService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private TaskCenterService taskCenterService;

    @Autowired
    private ImagePushService imagePushService;

    @Autowired
    private WaveService waveService;

    @Autowired
    private SaveServer saveServer;

    @Autowired
    private WebSocketWavePushService webSocketWavePushService;

    private final BlockingQueue<DeviceForShow> queue = new LinkedBlockingQueue<>();


    @Override
    public void push(DeviceForShow deviceForShow) {
        try {
            this.queue.put(deviceForShow);
        } catch (InterruptedException ignored) {

        }
    }

    private void checkPenetration(double u) {
        WeldingTasks taskInfo = saveServer.getTaskInfo();
        if (taskInfo == null) {
            return;
        }
        List<Penetration> penetrations = systemConfig.getPenetration();
        Penetration penetration = penetrations.stream().filter(item -> item.getMaterial().equals(taskInfo.getMaterial()) && item.getSize().equals(taskInfo.getSize()))
                .findFirst().orElse(null);
        if (penetration == null || u >= penetration.getVoltage()) {
            return;
        }
        //未熔透
        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setType(5);
        noticeMessage.setDegree(qualityEvaluationService.getInProcessOf().getDegree());
        imagePushService.push(noticeMessage);
    }

    private void checkAbnormalArcVoltage(double u) {
        if (u > systemConfig.getNormalVoltage()) {
            //弧压异常
            NoticeMessage noticeMessage = new NoticeMessage();
            //noticeMessage.setImage(socketListener.getImages());
            noticeMessage.setType(4);
            noticeMessage.setDegree(qualityEvaluationService.getInProcessOf().getDegree());
            imagePushService.push(noticeMessage);
        }
    }

    @Override
    public boolean running() {
        return (this.startTime.getTime() + (systemConfig.getDuration() * 1000)) > new Date().getTime();
    }

    @Override
    public void run() {
        while (true) {
            this.startTime = new Date();
            this.masterId = UUID.randomUUID().toString().replace("-", "");
            this.save = false;
            while (running()) {
                try {
                    DeviceForShow take = this.queue.poll(systemConfig.getArcClosingTime(), TimeUnit.SECONDS);
                    if (take == null) {
                        break;
                    }
                    webSocketWavePushService.setListData(take);
                    if (take.getU() <= systemConfig.getRestingVoltage()) {
                        continue;
                    }
                    this.save = true;
                    checkAbnormalArcVoltage(take.getU());
                    checkPenetration(take.getU());
                    if (take.getU() > systemConfig.getAbnormalVoltage() && taskCenterService.size() == 0) {
                        AnomalyDetection anomalyDetection = new AnomalyDetection(this.masterId);
                        taskCenterService.register(anomalyDetection);
                    }
                    taskCenterService.run(take);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            //存储
            if (this.save) {
                MasterWave masterWave = new MasterWave();
                masterWave.setId(masterId);
                masterWave.setStartTime(startTime);
                masterWave.setEndTime(new Date());
                waveService.insert(masterWave);
            }
        }
    }
}