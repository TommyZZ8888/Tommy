package com.vren.weldingmonitoring_java.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.vren.weldingmonitoring_java.domain.entity.UserOperateLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserOperateLogMapper extends MPJBaseMapper<UserOperateLogEntity> {

    @Update("truncate table user_operate_log")
    void clearOperateLogTable();

}