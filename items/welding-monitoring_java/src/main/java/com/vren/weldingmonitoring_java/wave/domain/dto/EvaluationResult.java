package com.vren.weldingmonitoring_java.wave.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class EvaluationResult {

    private List<StepData> stepData;

    private List<Double> variances;

    private String stability;

    private String quality;

}