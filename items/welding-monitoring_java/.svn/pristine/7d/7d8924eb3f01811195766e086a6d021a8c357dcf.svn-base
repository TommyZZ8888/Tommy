package com.vren.weldingmonitoring_java.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class LayerChannelDTO {

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

    @JsonProperty("layer_number")
    private Integer layerNumber;

    @JsonProperty("channel_number")
    private Integer channelNumber;

    private List<CraftDTO> craft;

}
