package com.vren.weldingmonitoring_java.wave;

import com.vren.weldingmonitoring_java.socket.server.DeviceForShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class TaskCenterService {

    private final List<QueueTask> tasks = new CopyOnWriteArrayList<>();

    private static final ExecutorService executorService = new ThreadPoolExecutor(300, 500, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

    @Autowired
    private Master master;

    @PostConstruct
    public void init() {
        executorService.execute(master);
    }

    public void register(QueueTask queueTask) {
        executorService.execute(queueTask);
        tasks.add(queueTask);
    }

    public int size() {
        return tasks.size();
    }

    public void remove(QueueTask queueTask) {
        tasks.remove(queueTask);
    }

    public void run(DeviceForShow deviceForShow) {
        for (QueueTask task : tasks) {
            task.push(deviceForShow);
        }
    }
}
