package com.vren.weldingmonitoring_java.wave.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vren.weldingmonitoring_java.wave.domain.dto.EvaluationResult;
import lombok.Data;

@Data
public class WeldingTaskMessage {
    @JsonProperty("task_no")
    private String taskNo;
    @JsonProperty("project_no")
    private String projectNo;
    @JsonProperty("line_no")
    private String lineNo;
    @JsonProperty("weldseam_no")
    private String weldseamNo;
    @JsonProperty("layer_number")
    private Integer layerNumber;
    @JsonProperty("channel_number")
    private Integer channelNumber;
    @JsonProperty("weld_tag")
    private String weldTag;

    private String material;

    @JsonProperty("left_stop")
    private Double leftStop;
    @JsonProperty("right_stop")
    private Double rightStop;
    private Double gap;
    private Double misalignment;
    @JsonProperty("weld_speed")
    private Double weldSpeed;
    @JsonProperty("wire_speed")
    private Double wireSpeed;


    private Double degree;
    private EvaluationResult result;
}
