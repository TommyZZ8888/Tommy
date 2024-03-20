package com.vren.weldingmonitoring_java.socket.server;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * @author GR
 * time 2023-07-03-09-13
 **/
@Data
public class DeviceForShow {

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private double i;

    private double u;

}
