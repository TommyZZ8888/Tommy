package com.vren.weldingmonitoring_java.domain.vo;

import com.vren.weldingmonitoring_java.wave.domain.dto.Error;
import lombok.Data;

import java.util.List;

@Data
public class PlaybackData {

    private List<PlaybackVO> playList;

    private List<Error> errors;
}
