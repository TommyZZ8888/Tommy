package com.vren.weldingmonitoring_java.statistics.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class StatisticsDTO {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("消耗值")
    private Double numerical;

    @ApiModelProperty("日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ApiModelProperty("类型 1:人员工时 2:设备能耗 3:耗材")
    private String type;
}
