package com.vren.weldingmonitoring_java;

import com.vren.weldingmonitoring_java.config.SystemConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeldingMonitoringJavaApplicationTests {


    @Autowired
    private SystemConfig systemConfig;

    @Test
    void contextLoads() throws Exception {
        System.out.println(systemConfig);
    }


}
