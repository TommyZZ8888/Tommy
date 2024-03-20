package com.vren.weldingmonitoring_java.wave.service;

import com.vren.weldingmonitoring_java.wave.domain.entity.MasterWave;
import com.vren.weldingmonitoring_java.wave.mapper.WaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaveService {

    @Autowired
    private WaveMapper waveMapper;

    public int insert(MasterWave wave) {
        return waveMapper.insert(wave);
    }

}
