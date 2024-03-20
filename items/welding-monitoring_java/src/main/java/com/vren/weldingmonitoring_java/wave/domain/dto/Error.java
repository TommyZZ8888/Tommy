package com.vren.weldingmonitoring_java.wave.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Error {

    public Error(Date dateTime, Integer type, String msg) {
        this.dateTime = dateTime;
        this.type = type;
        this.msg = msg;
    }

    private Date dateTime;

    /**
     * 1 未成形缺陷 >=5
     * 2 焊穿  >=1
     * 3 钨极烧损（严重） >=1
     * 4 弧压异常
     * 5 未熔透 >=1
     */
    private Integer type;

    private String msg;
}
