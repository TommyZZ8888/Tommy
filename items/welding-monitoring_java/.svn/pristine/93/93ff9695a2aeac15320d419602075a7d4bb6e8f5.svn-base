package com.vren.weldingmonitoring_java.wave.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("wave")
@Data
public class MasterWave {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private Date startTime;

    private Date endTime;
}
