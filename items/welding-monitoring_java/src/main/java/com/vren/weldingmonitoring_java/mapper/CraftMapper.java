package com.vren.weldingmonitoring_java.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.vren.weldingmonitoring_java.domain.entity.Craft;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author szp
 * @date 2023/6/30 15:54
 */
@Mapper
public interface CraftMapper extends MPJBaseMapper<Craft> {

    @Delete("delete from craft where layer_channel_id = #{layerChannelId}")
    void deleteByLayerChannelId(@Param("layerChannelId") String layerChannelId);

    @Select("select * from craft where layer_channel_id = #{layerChannelId} order by degree asc")
    List<Craft> selectByLayerChannelId(@Param("layerChannelId") String layerChannelId);

    @Select("select * from craft where layer_channel_id = #{layerChannelId} and degree = #{degree}")
    Craft selectByLayerChannelIdDegree(@Param("layerChannelId") String layerChannelId, @Param("degree") Double degree);


}