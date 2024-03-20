package com.vren.weldingmonitoring_java.statistics.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("statistics")
public class StatisticsEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("消耗值")
    private Double numerical;

    @ApiModelProperty("日期")
    private Date date;

    @ApiModelProperty("类型")
    private String type;
}
