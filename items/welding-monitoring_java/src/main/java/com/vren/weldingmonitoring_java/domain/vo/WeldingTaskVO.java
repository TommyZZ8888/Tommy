package com.vren.weldingmonitoring_java.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author szp
 * @version 1.0.0
 * @description
 * @date 2023/6/30
 */
@Data
public class WeldingTaskVO {

    private String id;

    @ApiModelProperty("项目号")
    private String projectNo;


    @ApiModelProperty("管线号")
    private String lineNo;

    @ApiModelProperty("焊口号")
    private String weldseamNo;

    @ApiModelProperty("任务号")
    private String taskNo;

    @ApiModelProperty("材质")
    private String material;

    @ApiModelProperty("牌号")
    private String rowNumber;

    public String getRowNumberNo() {
        return rowNumber;
    }

    public void setRowNumberNo(String rowNumberNo) {
        rowNumber = rowNumberNo;
    }

    @ApiModelProperty("壁厚")
    private String thick;

    @ApiModelProperty("结构")
    private String structure;

    @ApiModelProperty("规格")
    private String size;

    @ApiModelProperty("完成状态")
    private String taskStatus;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("创建时间")
    private Date createTime;
}