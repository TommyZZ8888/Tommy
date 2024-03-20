package com.vren.weldingmonitoring_java.statistics.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class StatisticsVO {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("消耗值")
    private Double numerical;

    @ApiModelProperty("日期")
    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ApiModelProperty("类型")
    private String type;
}
