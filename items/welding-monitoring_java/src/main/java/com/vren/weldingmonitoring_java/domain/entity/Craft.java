package com.vren.weldingmonitoring_java.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * craft
 *
 * @author
 */
@TableName("craft")
@Data
public class Craft {

    @TableField("id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String layerChannelId;

    /**
     * 度数
     */
    private Double degree;

    /**
     * 间隙mm
     */
    private Double gap;

    /**
     * 错边量mm
     */
    private Double misalignment;

    /**
     * 电流A
     */
    private Double current;

    /**
     * 焊接速度m/min
     */
    private Double weldSpeed;

    /**
     * 送丝速度m/min
     */
    private Double wireSpeed;

    /**
     * 摆长mm
     */
    private Double weaveLen;

    /**
     * 摆动偏转mm
     */
    private Double weaveDeflection;

    /**
     * 左停留s
     */
    private Double leftStop;

    /**
     * 右停留s
     */
    private Double rightStop;


}