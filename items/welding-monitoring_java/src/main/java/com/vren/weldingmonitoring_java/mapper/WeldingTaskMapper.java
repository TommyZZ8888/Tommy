package com.vren.weldingmonitoring_java.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.vren.weldingmonitoring_java.domain.entity.WeldingTasks;
import com.vren.weldingmonitoring_java.domain.vo.WeldingTaskStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author szp
 * @date 2023/6/30 15:54
 */
@Mapper
public interface WeldingTaskMapper extends MPJBaseMapper<WeldingTasks> {

    @Select("select * from welding_tasks where task_no = #{taskNo}")
    WeldingTasks selectByPrimary(@Param("taskNo") String taskNo);

    @Select("select task_status,count(0) as count from welding_tasks group by task_status")
    List<WeldingTaskStatistics> statistics();
}