package com.vren.weldingmonitoring_java.wave.service;

import com.vren.weldingmonitoring_java.wave.domain.entity.AnomalyData;
import com.vren.weldingmonitoring_java.wave.mapper.AnomalyDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnomalyDataService {
    @Autowired
    private AnomalyDataMapper anomalyDataMapper;

    public int insert(AnomalyData data) {
        return anomalyDataMapper.insert(data);
    }

}
