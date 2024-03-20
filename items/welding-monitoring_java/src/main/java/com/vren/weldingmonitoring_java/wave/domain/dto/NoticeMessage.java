package com.vren.weldingmonitoring_java.wave.domain.dto;

import lombok.Data;

@Data
public class NoticeMessage {

    private String image;

    /**
     * 2 焊穿
     * 4 弧压异常
     */
    private int type;

    private Double degree;
}
