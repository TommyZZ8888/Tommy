package com.vren.weldingmonitoring_java.controller;

import com.vren.weldingmonitoring_java.anno.NoNeedLogin;
import com.vren.weldingmonitoring_java.anno.OperateLog;
import com.vren.weldingmonitoring_java.common.domain.ResponseResult;
import com.vren.weldingmonitoring_java.common.utils.BeanUtil;
import com.vren.weldingmonitoring_java.domain.dto.LayerChannelDTO;
import com.vren.weldingmonitoring_java.domain.dto.QueryDegreeDTO;
import com.vren.weldingmonitoring_java.domain.entity.Craft;
import com.vren.weldingmonitoring_java.domain.vo.CraftVO;
import com.vren.weldingmonitoring_java.domain.vo.LayerChannelVO;
import com.vren.weldingmonitoring_java.service.CraftService;
import com.vren.weldingmonitoring_java.service.LayerChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/layerChannel")
@Api(tags = "层道控制器")
@NoNeedLogin
@OperateLog
public class LayerChannelController {

    @Autowired
    private LayerChannelService layerChannelService;

    @Autowired
    private CraftService craftService;

    @RequestMapping(value = "/addOrUpdateLayerChannel", method = RequestMethod.POST)
    @ApiOperation("新增层道")
    public ResponseResult<Boolean> addOrUpdateLayerChannel(@RequestBody @Valid List<LayerChannelDTO> list) {
        layerChannelService.addOrUpdateLayerChannel(list);
        return ResponseResult.success("操作成功");
    }

    @RequestMapping(value = "/getLayerChannel", method = RequestMethod.POST)
    @ApiOperation("获取层道")
    public ResponseResult<List<LayerChannelVO>> getLayerChannel(@RequestParam("taskId") String taskId) {
        return ResponseResult.success("获取成功", layerChannelService.getLayerChannel(taskId));
    }


    @RequestMapping(value = "/getCraft", method = RequestMethod.POST)
    @ApiOperation("获取焊接参数")
    public ResponseResult<List<CraftVO>> getCraft(@RequestParam("layerChannelId") String layerChannelId) {
        return ResponseResult.success("获取成功", craftService.selectByLayerChannelId(layerChannelId));
    }

    @RequestMapping(value = "/getDegree", method = RequestMethod.POST)
    @ApiOperation("获取度数详情")
    public ResponseResult<CraftVO> getDegree(@RequestBody QueryDegreeDTO dto) {
        Craft craft = craftService.selectByLayerChannelIdDegree(dto.getLayerChannelId(), dto.getDegree());
        if (craft == null) {
            return ResponseResult.error("未查询到信息");
        }
        return ResponseResult.success("查询成功", BeanUtil.copy(craft, CraftVO.class));
    }
}