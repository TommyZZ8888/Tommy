package com.vren.weldingmonitoring_java.statistics;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.vren.weldingmonitoring_java.common.domain.PageResult;
import com.vren.weldingmonitoring_java.common.utils.BeanUtil;
import com.vren.weldingmonitoring_java.common.utils.PageUtil;
import com.vren.weldingmonitoring_java.statistics.domain.dto.ListDTO;
import com.vren.weldingmonitoring_java.statistics.domain.dto.QueryDTO;
import com.vren.weldingmonitoring_java.statistics.domain.dto.StatisticsDTO;
import com.vren.weldingmonitoring_java.statistics.domain.entity.StatisticsEntity;
import com.vren.weldingmonitoring_java.statistics.domain.vo.StatisticsVO;
import com.vren.weldingmonitoring_java.utils.SearchUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    public void add(List<StatisticsDTO> list) {
        List<StatisticsEntity> statisticsEntities = BeanUtil.copyList(list, StatisticsEntity.class);
        for (StatisticsEntity entity : statisticsEntities) {
            statisticsMapper.insert(entity);
        }
    }


    public Map<String, List<StatisticsVO>> query(QueryDTO dto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        MPJLambdaWrapper<StatisticsEntity> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(StatisticsEntity::getId)
                .select(StatisticsEntity::getType)
                .selectSum(StatisticsEntity::getNumerical)
                .select(StatisticsEntity::getDate)
                .select(StatisticsEntity::getName)
                .eq(StatisticsEntity::getType, dto.getType());
        SearchUtil.timeRangeSearch(wrapper, StatisticsEntity::getDate, dto.getStartTime(), dto.getEndTime());
        wrapper.groupBy(StatisticsEntity::getName);
        List<StatisticsEntity> list = statisticsMapper.selectList(wrapper);
        return list.stream()
                .map(e -> BeanUtil.copy(e, StatisticsVO.class))
                .collect(Collectors.groupingBy(
                        k -> simpleDateFormat.format(k.getDate())
                ));
    }


    public PageResult<StatisticsVO> list(ListDTO dto) {
        MPJLambdaWrapper<StatisticsEntity> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(StatisticsEntity.class);
        wrapper.eq(StringUtils.isNotBlank(dto.getType()), StatisticsEntity::getType, dto.getType());
        Page<StatisticsEntity> page = PageUtil.convert2QueryPage(dto);
        IPage<StatisticsVO> selectPage = statisticsMapper.selectJoinPage(page, StatisticsVO.class, wrapper);
        PageResult<StatisticsVO> res = PageUtil.convert2PageResult(selectPage);
        return res;
    }
}
