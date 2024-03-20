package com.vren.weldingmonitoring_java.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.vren.weldingmonitoring_java.domain.entity.LayerChannel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author szp
 * @date 2023/6/30 15:54
 */
@Mapper
public interface LayerChannelMapper extends MPJBaseMapper<LayerChannel> {

    @Select("select * from layer_channel where task_id = #{taskId} and layer_number = #{layerNumber} and channel_number = #{channelNumber}")
    LayerChannel selectByPrimary(
            @Param("taskId") String taskId,
            @Param("layerNumber") Integer layerNumber,
            @Param("channelNumber") Integer channelNumber
    );

    @Select("select * from layer_channel where task_id = #{taskId}")
    List<LayerChannel> selectListByTaskId(@Param("taskId") String taskId);
}
