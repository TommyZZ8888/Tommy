package com.vren.weldingmonitoring_java.config;

import com.vren.weldingmonitoring_java.domain.entity.Penetration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("system")
public class SystemConfig {

    private Long normalVoltage;

    private Long arcClosingTime;

    private Long restingVoltage;

    private Long abnormalVoltage;

    private Long abnormalDuration;

    private Long duration;

    private String imagePushHost;

    private int imagePushPort;

    private String weldingTaskPushHost;

    private int weldingTaskPushPort;

    private String dataHost;

    private int dataPort;

    private String devId;

    private String robotStepFilePath;

    private List<List<Object>> evaluateRule;

    private List<Penetration> penetration;

}
