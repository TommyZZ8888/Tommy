package com.vren.weldingmonitoring_java.wave;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vren.weldingmonitoring_java.config.SystemConfig;
import com.vren.weldingmonitoring_java.socket.server.DeviceForShow;
import com.vren.weldingmonitoring_java.wave.socket.client.SocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class SocketSourceData extends SocketClient {


    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private Master master;

    private DeviceForShow deviceForShow;

    public DeviceForShow getDeviceForShow() {
        return deviceForShow;
    }

    @Override
    public void onConnectAsync() {
        while (true) {
            try {
                write(systemConfig.getDevId(), false);
                Thread.sleep(100);
            } catch (Exception ignored) {

            }
        }
    }

    @Override
    public String getHost() {
        return systemConfig.getDataHost();
    }

    @Override
    public Integer getPort() {
        return systemConfig.getDataPort();
    }

    @Override
    public void onMessage(Object message) {
        JSONObject jsonObject = JSONObject.parseObject(message.toString());
        if (jsonObject == null) {
            return;
        }
        JSONArray arcUs = jsonObject.getJSONArray("ArcUs");
        JSONArray weldIs = jsonObject.getJSONArray("WeldIs");
        int size = Math.min(arcUs == null ? 0 : arcUs.size(), weldIs == null ? 0 : weldIs.size());
        for (int i = 0; i < size; i++) {
            DeviceForShow deviceForShow = new DeviceForShow();
            deviceForShow.setI(weldIs.getDouble(i));
            deviceForShow.setU(arcUs.getDouble(i));
            deviceForShow.setTime(new Date());
            this.deviceForShow = deviceForShow;
            master.push(deviceForShow);
        }
    }

}
