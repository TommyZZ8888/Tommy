package com.vren.weldingmonitoring_java.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("layer_channel")
public class LayerChannel {

    @TableField("id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String taskId;

    @ApiModelProperty("项目号")
    private String projectNo;


    @ApiModelProperty("管线号")
    private String lineNo;

    @ApiModelProperty("焊口号")
    private String weldseamNo;

    @ApiModelProperty("任务号")
    private String taskNo;

    private Integer layerNumber;

    private Integer channelNumber;
}
