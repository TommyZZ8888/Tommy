package com.vren.weldingmonitoring_java.wave;

import com.vren.weldingmonitoring_java.common.utils.FeignClientFactory;
import com.vren.weldingmonitoring_java.config.SystemConfig;
import com.vren.weldingmonitoring_java.wave.domain.vo.WeldingTaskMessage;
import com.vren.weldingmonitoring_java.wave.service.feign.WeldingTaskPushFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WeldingTaskPushService {

    @Autowired
    SystemConfig systemConfig;

    private String getUrl() {
        return String.format("http://%s:%d", systemConfig.getWeldingTaskPushHost(), systemConfig.getWeldingTaskPushPort());
    }

    //起弧
    public void startCollect(WeldingTaskMessage weldingTaskMessage) {
        FeignClientFactory.create(WeldingTaskPushFeign.class, getUrl()).startCollect(weldingTaskMessage);
    }

    //息弧
    public void stopCollect(WeldingTaskMessage weldingTaskMessage) {
        FeignClientFactory.create(WeldingTaskPushFeign.class, getUrl()).stopCollect(weldingTaskMessage);

    }

    //旋转角度
    public void degree(WeldingTaskMessage weldingTaskMessage) {
        FeignClientFactory.create(WeldingTaskPushFeign.class, getUrl()).degree(weldingTaskMessage);
    }

}
