package com.vren.weldingmonitoring_java.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vren.weldingmonitoring_java.common.domain.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeldingTaskQueryDTO extends PageParam {

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
}
