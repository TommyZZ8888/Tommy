package com.vren.weldingmonitoring_java.wave;

import com.vren.weldingmonitoring_java.domain.entity.WeldingTasks;
import com.vren.weldingmonitoring_java.socket.server.DeviceForShow;
import com.vren.weldingmonitoring_java.socket.server.SocketListener;
import com.vren.weldingmonitoring_java.utils.MyThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class SaveServer {

    @Autowired
    private SocketListener socketListener;

    @Autowired
    private SocketSourceData socketSourceData;

    private final HashMap<String, FileWriter> fileWriterHashMap = new HashMap<>();

    private Timer timer;

    private WeldingTasks taskInfo;

    public WeldingTasks getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(WeldingTasks taskInfo) {
        this.taskInfo = taskInfo;
    }

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private final AtomicLong line = new AtomicLong(0);
    private long count = 0;

    private String degree;

    private String sn;

    private String layerNumber;

    private String channelNumber;

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 100L, TimeUnit.SECONDS
            , new LinkedBlockingDeque<>(2000), MyThreadFactory.create("SaveServer"));

    public String getSn() {
        return sn;
    }

    public String getDegree() {
        return degree;
    }

    public String getLayerNumber() {
        return layerNumber;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void start(String sn, String layerNumber, String channelNumber) {
        line.set(0);
        count = 0;
        timer = new Timer();
        this.sn = sn;
        this.layerNumber = layerNumber;
        this.channelNumber = channelNumber;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (degree != null) {
                    long c = count;
                    executor.execute(() -> handle(degree, c));
                    count++;
                }
            }
        }, 0, 250);
    }

    public void degree(String degree) {
        this.degree = degree;
        try {
            String format = String.format("data/%s/%s-%s", sn, layerNumber, channelNumber);
            File file = new File(format);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file1 = new File(format + "/" + degree);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            FileWriter fileWriter = new FileWriter(String.format("%s/%s.data", format, degree), true);
            this.fileWriterHashMap.put(degree, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.sn = null;
        this.degree = null;
        timer.cancel();
        for (FileWriter value : fileWriterHashMap.values()) {
            try {
                value.flush();
                value.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileWriterHashMap.clear();
    }

    private void handle(String degree, long count) {
        DeviceForShow deviceForShow = socketSourceData.getDeviceForShow();
        String image = socketListener.getImages();
        if (image == null || deviceForShow == null) {
            return;
        }
        String imagePath = String.format("data/%s/%s-%s/%s/%s", sn, layerNumber, channelNumber, degree, deviceForShow.getTime().getTime());
        String device = String.format("%f,%f,%s\r\n", deviceForShow.getI(), deviceForShow.getU(), imagePath);
        try {
            lock.lock();
            while (line.get() != count) {
                condition.await();
            }
            FileWriter fileWriter = fileWriterHashMap.get(degree);
            fileWriter.write(device);
            fileWriter.flush();
            condition.signalAll();
            FileWriter fileWriter1 = new FileWriter(imagePath);
            fileWriter1.write(image);
            fileWriter1.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            line.addAndGet(1);
            lock.unlock();
        }
    }
}
