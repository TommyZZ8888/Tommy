package com.vren.weldingmonitoring_java.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author GR
 * time 2023-06-30-13-32
 **/
@Data
@TableName("welding_tasks")
public class WeldingTasks {

    @TableField("id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("项目号")
    private String projectNo;


    @ApiModelProperty("管线号")
    private String lineNo;

    @ApiModelProperty("焊口号")
    private String weldseamNo;

    @ApiModelProperty("任务号")
    private String taskNo;

    private String rowNumberNo;

    @ApiModelProperty("材质")
    private String material;

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

    private Date createTime;

}
