package com.vren.weldingmonitoring_java.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GR
 * time 2023-06-30-13-32
 **/
@Data
@TableName("user")
public class User {

    @TableField("id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @ApiModelProperty("工号")
    private String jobNumber;

    private String password;

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("岗位")
    private String post;

    @ApiModelProperty("图片路径")
    private String imagePath;

}
