package com.vren.weldingmonitoring_java.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * craft
 *
 * @author
 */
@TableName("craft")
@Data
public class CraftDTO {

    /**
     * 度数
     */
    @ApiModelProperty("度数")
    private Double degree;

    /**
     * 间隙mm
     */
    @ApiModelProperty("间隙mm")
    private Double gap;

    /**
     * 错边量mm
     */
    @ApiModelProperty("错边量mm")
    private Double misalignment;

    /**
     * 电流A
     */
    @ApiModelProperty("电流A")
    private Double current;

    /**
     * 焊接速度m/min
     */
    @ApiModelProperty("焊接速度m/min")
    @JsonProperty("weld_speed")
    private Double weldSpeed;

    /**
     * 送丝速度m/min
     */
    @ApiModelProperty("送丝速度m/min")
    @JsonProperty("wire_speed")
    private Double wireSpeed;

    /**
     * 摆长mm
     */
    @ApiModelProperty("摆长mm")
    @JsonProperty("weave_len")
    private Double weaveLen;

    /**
     * 摆动偏转mm
     */
    @ApiModelProperty("摆动偏转mm")
    @JsonProperty("weave_deflection")
    private Double weaveDeflection;

    /**
     * 左停留s
     */
    @ApiModelProperty("左停留s")
    @JsonProperty("left_stop")
    private Double leftStop;

    /**
     * 右停留s
     */
    @ApiModelProperty("右停留s")
    @JsonProperty("right_stop")
    private Double rightStop;


}