package com.vren.weldingmonitoring_java.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author szp
 * @date 2023/6/30 15:53
 */
@Data
public class WeldingTaskDTO {

    @ApiModelProperty("项目号")
    @JsonProperty("project_no")
    private String projectNo;

    @ApiModelProperty("管线号")
    @JsonProperty("line_no")
    private String lineNo;

    @ApiModelProperty("焊口号")
    @JsonProperty("weldseam_no")
    private String weldseamNo;

    @ApiModelProperty("任务号")
    @JsonProperty("task_no")
    private String taskNo;

    @ApiModelProperty("材质")
    private String material;

    @ApiModelProperty("牌号")
    @JsonProperty("row_number")
    private String rowNumber;

    public String getRowNumberNo() {
        return rowNumber;
    }

    @ApiModelProperty("壁厚")
    private String thick;

    @ApiModelProperty("结构")
    private String structure;

    @ApiModelProperty("规格")
    private String size;

    @ApiModelProperty("任务状态")
    @JsonProperty("task_status")
    private String taskStatus;

    @ApiModelProperty("任务开始时间")
    @JsonProperty("start_time")
    private Date startTime;

    @ApiModelProperty("任务结束时间")
    @JsonProperty("end_time")
    private Date endTime;
}