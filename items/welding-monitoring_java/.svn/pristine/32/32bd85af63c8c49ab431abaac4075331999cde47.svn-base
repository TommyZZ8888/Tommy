package com.vren.weldingmonitoring_java.service;

import com.vren.weldingmonitoring_java.common.utils.BeanUtil;
import com.vren.weldingmonitoring_java.domain.dto.CraftDTO;
import com.vren.weldingmonitoring_java.domain.dto.LayerChannelDTO;
import com.vren.weldingmonitoring_java.domain.entity.Craft;
import com.vren.weldingmonitoring_java.domain.entity.LayerChannel;
import com.vren.weldingmonitoring_java.domain.entity.WeldingTasks;
import com.vren.weldingmonitoring_java.domain.vo.LayerChannelVO;
import com.vren.weldingmonitoring_java.mapper.LayerChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayerChannelService {

    @Autowired
    private LayerChannelMapper layerChannelMapper;

    @Autowired
    private WeldingTaskService weldingTaskService;

    @Autowired
    private CraftService craftService;

    public void addOrUpdateLayerChannel(List<LayerChannelDTO> list) {
        for (LayerChannelDTO dto : list) {
            LayerChannel copy = BeanUtil.copy(dto, LayerChannel.class);
            WeldingTasks weldingTasks = weldingTaskService.selectByPrimary(dto.getTaskNo());
            if (weldingTasks == null) {
                continue;
            }
            LayerChannel layerChannel = selectByPrimary(weldingTasks.getId(), dto.getLayerNumber(), dto.getChannelNumber());
            copy.setTaskId(weldingTasks.getId());
            if (layerChannel == null) {
                layerChannelMapper.insert(copy);
            } else {
                copy.setId(layerChannel.getId());
                layerChannelMapper.updateById(copy);
                craftService.deleteByLayerChannelId(layerChannel.getId());
            }
            List<CraftDTO> craft = dto.getCraft();
            for (CraftDTO craftDTO : craft) {
                Craft copy1 = BeanUtil.copy(craftDTO, Craft.class);
                copy1.setLayerChannelId(copy.getId());
                craftService.insert(copy1);
            }
        }
    }

    public LayerChannel selectByPrimary(String taskId, Integer layerNumber, Integer channelNumber) {
        return layerChannelMapper.selectByPrimary(taskId, layerNumber, channelNumber);
    }

    public List<LayerChannelVO> getLayerChannel(String taskId) {
        List<LayerChannel> layerChannels = layerChannelMapper.selectListByTaskId(taskId);
        return BeanUtil.copyList(layerChannels, LayerChannelVO.class);
    }

}
