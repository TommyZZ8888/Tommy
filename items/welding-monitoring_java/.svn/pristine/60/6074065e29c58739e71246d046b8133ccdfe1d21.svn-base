package com.vren.weldingmonitoring_java.controller;

import cn.hutool.core.text.AntPathMatcher;
import com.vren.weldingmonitoring_java.anno.NoNeedLogin;
import com.vren.weldingmonitoring_java.anno.OperateLog;
import com.vren.weldingmonitoring_java.common.domain.PageResult;
import com.vren.weldingmonitoring_java.common.domain.ResponseResult;
import com.vren.weldingmonitoring_java.domain.dto.*;
import com.vren.weldingmonitoring_java.domain.vo.PlaybackData;
import com.vren.weldingmonitoring_java.domain.vo.WeldingTaskStatistics;
import com.vren.weldingmonitoring_java.domain.vo.WeldingTaskVO;
import com.vren.weldingmonitoring_java.service.WeldingTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/weldingTask")
@Api(tags = "焊接任务控制器")
@NoNeedLogin
public class WeldingTaskController {
    @Autowired
    private WeldingTaskService weldingTaskService;

    @RequestMapping(value = "/addOrUpdateWeldingTask", method = RequestMethod.POST)
    @ApiOperation("新增任务")
    public ResponseResult<Boolean> addOrUpdateWeldingTask(@RequestBody @Valid WeldingTaskDTO dto) {
        weldingTaskService.addOrUpdate(dto);
        return ResponseResult.success("操作成功");
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ApiOperation("返回焊接任务列表")
    public ResponseResult<PageResult<WeldingTaskVO>> selectList(@RequestBody WeldingTaskQueryDTO dto) {
        return ResponseResult.success("操作成功", weldingTaskService.queryList(dto));
    }

    @RequestMapping(value = "/signal", method = RequestMethod.POST)
    @ApiOperation("接受起弧息弧信号")
    @OperateLog
    public ResponseResult<Boolean> signal(@RequestBody SignalDTO dto) {
        weldingTaskService.signal(dto);
        return ResponseResult.success("操作成功");
    }

    @RequestMapping(value = "/degree", method = RequestMethod.POST)
    @ApiOperation("旋转角度")
    @OperateLog
    public ResponseResult<Boolean> degree(@RequestBody DegreeDTO dto) {
        weldingTaskService.degree(dto);
        return ResponseResult.success("操作成功");
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    @ApiOperation("任务完成的统计")
    public ResponseResult<List<WeldingTaskStatistics>> statistics() {
        return ResponseResult.success("获取成功", weldingTaskService.statistics());
    }


    @RequestMapping(value = "/playback", method = RequestMethod.POST)
    @ApiOperation("回放")
    public ResponseResult<PlaybackData> playback(@RequestBody PlaybackDTO dto, HttpServletResponse response) {
        return ResponseResult.success("获取成功", weldingTaskService.playback(dto, response));
    }

    @RequestMapping(value = "/image/{path}/**", method = RequestMethod.GET)
    @ApiOperation("获取图片流")
    public void image(@PathVariable String path, HttpServletRequest request, HttpServletResponse response) {
        final String pathq = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern =
                request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, pathq);
        String moduleName;
        if (null != arguments && !arguments.isEmpty()) {
            moduleName = path + '/' + arguments;
        } else {
            moduleName = path;
        }
        weldingTaskService.image(moduleName, response);
    }

}
