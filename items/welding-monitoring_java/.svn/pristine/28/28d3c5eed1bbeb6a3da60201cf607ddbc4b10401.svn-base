package com.vren.weldingmonitoring_java.service;

import com.vren.weldingmonitoring_java.domain.entity.UserOperateLogEntity;
import com.vren.weldingmonitoring_java.mapper.UserOperateLogMapper;
import com.vren.weldingmonitoring_java.utils.MyThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@EnableScheduling
public class UserOperateLogService {

    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private UserOperateLogMapper userOperateLogMapper;


    @PostConstruct
    void init() {
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS
                    , new LinkedBlockingDeque<>(2000), MyThreadFactory.create("LogAspect"));
        }
    }

    @PreDestroy
    void destroy() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
            threadPoolExecutor = null;
        }
    }


    public void addLog(UserOperateLogEntity userOperateLogEntity) {
        try {
            threadPoolExecutor.execute(() -> userOperateLogMapper.insert(userOperateLogEntity));
        } catch (Throwable e) {
            log.error("userLogAfterAdvice:", e);
        }
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void clearOperateLogTable() {
        userOperateLogMapper.clearOperateLogTable();
    }

}
