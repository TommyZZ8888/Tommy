package com.vren.weldingmonitoring_java.statistics;

import com.vren.weldingmonitoring_java.common.domain.PageResult;
import com.vren.weldingmonitoring_java.common.domain.ResponseResult;
import com.vren.weldingmonitoring_java.statistics.domain.dto.ListDTO;
import com.vren.weldingmonitoring_java.statistics.domain.dto.QueryDTO;
import com.vren.weldingmonitoring_java.statistics.domain.dto.StatisticsDTO;
import com.vren.weldingmonitoring_java.statistics.domain.vo.StatisticsVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"人员工时", "设备能耗", "耗材"})
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<Boolean> add(@RequestBody List<StatisticsDTO> list) {
        statisticsService.add(list);
        return ResponseResult.success("添加成功");
    }


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseResult<Map<String, List<StatisticsVO>>> query(@RequestBody QueryDTO dto) {
        return ResponseResult.success("查询成功", statisticsService.query(dto));
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<StatisticsVO>> list(@RequestBody ListDTO dto) {
        return ResponseResult.success("查询成功", statisticsService.list(dto));
    }


}
