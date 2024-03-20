package com.vren.weldingmonitoring_java.wave;

import com.vren.weldingmonitoring_java.config.SystemConfig;
import com.vren.weldingmonitoring_java.socket.server.DeviceForShow;
import com.vren.weldingmonitoring_java.socket.server.SocketListener;
import com.vren.weldingmonitoring_java.utils.ApplicationContextUtil;
import com.vren.weldingmonitoring_java.wave.domain.dto.NoticeMessage;
import com.vren.weldingmonitoring_java.wave.domain.entity.AnomalyData;
import com.vren.weldingmonitoring_java.wave.service.AnomalyDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class AnomalyDetection implements QueueTask {

    private QualityEvaluationService qualityEvaluationService;

    private final BlockingQueue<DeviceForShow> queue = new LinkedBlockingQueue<>();

    private AnomalyDataService anomalyDataService;

    private String masterId;

    private TaskCenterService taskCenterService;

    private ImagePushService imagePushService;

    private SystemConfig systemConfig;

    private List<String> images;

    private List<DeviceForShow> signal;

    private SocketListener socketListener;

    private Date startTime;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss-SSS");

    public AnomalyDetection(String masterId) {
        this.masterId = masterId;
        systemConfig = ApplicationContextUtil.getBean(SystemConfig.class);
        socketListener = ApplicationContextUtil.getBean(SocketListener.class);
        taskCenterService = ApplicationContextUtil.getBean(TaskCenterService.class);
        anomalyDataService = ApplicationContextUtil.getBean(AnomalyDataService.class);
        imagePushService = ApplicationContextUtil.getBean(ImagePushService.class);
        qualityEvaluationService = ApplicationContextUtil.getBean(QualityEvaluationService.class);
        startTime = new Date();
        images = new ArrayList<>();
        signal = new ArrayList<>();
    }

    @Override
    public void push(DeviceForShow deviceForShow) {
        try {
            this.queue.put(deviceForShow);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean running() {
        long l = new Date().getTime() - startTime.getTime();
        return l - systemConfig.getAbnormalDuration() <= 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DeviceForShow take = this.queue.poll(1, TimeUnit.SECONDS);
                if (take == null || take.getU() < systemConfig.getAbnormalVoltage() || !running()) {
                    break;
                }
                signal.add(take);
                images.add(socketListener.getImages());
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }
        }
        taskCenterService.remove(this);
        if (!this.running()) {
            NoticeMessage noticeMessage = new NoticeMessage();
            noticeMessage.setType(2);
            noticeMessage.setImage(socketListener.getImages());
            noticeMessage.setDegree(qualityEvaluationService.getInProcessOf().getDegree());
            imagePushService.push(noticeMessage);
            //正常结束需要存储数据
            String collect = signal.stream().map(item -> {
                String[] strings = new String[]{
                        String.valueOf(item.getTime().getTime()),
                        String.valueOf(item.getI()),
                        String.valueOf(item.getU()),
                };
                return String.join(",", strings);
            }).collect(Collectors.joining("\r\n"));
            String format = simpleDateFormat.format(startTime);
            File file = getFile(format, format, "txt");
            try {
                FileUtils.writeStringToFile(file, collect, StandardCharsets.UTF_8, true);
                for (String image : images) {
                    if (image == null) continue;
                    base64ToImg(image, getFile(format, UUID.randomUUID().toString(), "png"));
                }
                AnomalyData anomalyData = new AnomalyData();
                anomalyData.setWaveId(masterId);
                anomalyData.setPath(file.getParent());
                anomalyDataService.insert(anomalyData);
            } catch (IOException e) {
                log.info(e.getMessage());
            }

        }
    }

    private File getFile(String dir, String name, String ext) {
        Calendar instance = Calendar.getInstance();
        String format = String.format("./result/%d/%d/%d/%s/%s.%s", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH) + 1, instance.get(Calendar.DAY_OF_MONTH), dir, name, ext);
        return new File(format);
    }

    public void base64ToImg(String base64, File file) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(base64);
        try (OutputStream outputStream = new FileOutputStream(file)) {
            // 处理数据
            for (int i = 0; i < decode.length; ++i) {
                if (decode[i] < 0) {
                    decode[i] += 256;
                }
            }
            outputStream.write(decode);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
