package com.vren.weldingmonitoring_java.wave.domain.dto;

import com.vren.weldingmonitoring_java.socket.server.DeviceForShow;
import lombok.Data;

import java.util.List;

@Data
public class StepData {

    //摆长
    private Double weaveLen;
    //左停留
    private Double leftStop;
    //右停留
    private Double rightStop;
    private Double weaveDeflection;
    //焊接速度
    /**
     * 度数
     */
    private Double degree;

    /**
     * 间隙mm
     */
    private Double gap;

    /**
     * 错边量mm
     */
    private Double misalignment;

    /**
     * 电流A
     */
    private Double current;
    private Double weldSpeed;

    private Double wireSpeed;

    private Double T;

    private List<DeviceForShow> list;

    private List<Error> errors;

    private Double variance;
}