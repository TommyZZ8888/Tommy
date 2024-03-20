package com.vren.weldingmonitoring_java.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class InfluxTable {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @JsonProperty(value = "0")
    private Date time;

    @JsonProperty(value = "1")
    private String devNo;

    @JsonProperty(value = "2")
    private String gas;

    @JsonProperty(value = "3")
    private String i;

    @JsonProperty(value = "4")
    private String isStart;

    @JsonProperty(value = "5")
    private String u;

    @JsonProperty(value = "6")
    private String waveId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @JsonProperty(value = "7")
    private Date wavePointDate;
}
