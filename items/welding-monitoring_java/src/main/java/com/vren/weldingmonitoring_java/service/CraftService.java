package com.vren.weldingmonitoring_java.service;

import com.vren.weldingmonitoring_java.common.utils.BeanUtil;
import com.vren.weldingmonitoring_java.domain.entity.Craft;
import com.vren.weldingmonitoring_java.domain.vo.CraftVO;
import com.vren.weldingmonitoring_java.mapper.CraftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CraftService {

    @Autowired
    private CraftMapper craftMapper;

    public void insert(Craft craft) {
        craftMapper.insert(craft);
    }

    public void deleteByLayerChannelId(String layerChannelId) {
        craftMapper.deleteByLayerChannelId(layerChannelId);
    }

    public List<CraftVO> selectByLayerChannelId(String layerChannelId) {
        return BeanUtil.copyList(craftMapper.selectByLayerChannelId(layerChannelId), CraftVO.class);
    }

    public Craft selectByLayerChannelIdDegree(String layerChannelId, Double degree) {
        return craftMapper.selectByLayerChannelIdDegree(layerChannelId, degree);
    }


}
