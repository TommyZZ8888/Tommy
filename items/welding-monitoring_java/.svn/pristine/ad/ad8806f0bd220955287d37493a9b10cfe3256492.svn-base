package com.vren.weldingmonitoring_java.wave.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("anomaly_data")
@Data
public class AnomalyData {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String waveId;

    private String path;
}
