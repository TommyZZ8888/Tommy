package com.vren.weldingmonitoring_java.wave;

import com.alibaba.fastjson.JSONObject;
import com.vren.weldingmonitoring_java.config.SystemConfig;
import com.vren.weldingmonitoring_java.socket.server.DeviceForShow;
import com.vren.weldingmonitoring_java.wave.domain.dto.Error;
import com.vren.weldingmonitoring_java.wave.domain.dto.EvaluationResult;
import com.vren.weldingmonitoring_java.wave.domain.dto.StepData;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QualityEvaluationService {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private SaveServer saveServer;

    private static final HashMap<Integer, String> err = new HashMap<>() {{
        put(1, "未成形缺陷");
        put(2, "焊穿");
        put(3, "钨极烧损");
        put(4, "弧压异常");
        put(5, "未熔透");
    }};

    private boolean isStart = false;

    private final List<StepData> list = new ArrayList<>();

    private StepData inProcessOf;

    public StepData getInProcessOf() {
        if (inProcessOf == null) {
            return new StepData();
        }
        return inProcessOf;
    }

    public void setForShowList(DeviceForShow deviceForShow) {
        if (inProcessOf != null) {
            this.inProcessOf.getList().add(deviceForShow);
        }
    }

    public void setErrors(Date date, Integer type) {
        if (!isStart()) {
            return;
        }
        String msg = err.get(type);
        if (inProcessOf != null) {
            inProcessOf.getErrors().add(new Error(date, type, msg));
        }
    }

    public void setInProcessOf(StepData stepData) {
        stepData.setList(new ArrayList<>());
        stepData.setErrors(new ArrayList<>());
        list.add(stepData);
        inProcessOf = stepData;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean start() {
        if (isStart) {
            return true;
        }
        isStart = true;
        return true;
    }

    public EvaluationResult stop() {
        EvaluationResult evaluationResult = new EvaluationResult();
        if (!isStart) {
            return evaluationResult;
        }
        isStart = false;
        File file = getFile();
        EvaluationResult recordResult = null;
        if (file.exists()) {
            try {
                String record = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                recordResult = JSONObject.parseObject(record, EvaluationResult.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<StepData> stepDataList = new ArrayList<>(this.list);
        if (recordResult != null) {
            stepDataList.addAll(0, recordResult.getStepData());
        }
        for (StepData stepData : stepDataList) {
            List<DeviceForShow> list = stepData.getList();
            OptionalDouble optionalDouble = list.stream().mapToDouble(DeviceForShow::getU).average();
            if (optionalDouble.isEmpty()) {
                continue;
            }
            double average = optionalDouble.getAsDouble();
            double variance = list.stream().mapToDouble(item -> Math.pow(item.getU() - average, 2.0)).sum() / list.size();
            BigDecimal bigDecimal = new BigDecimal(variance);
            stepData.setVariance(bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
        evaluationResult.setStepData(stepDataList);
        List<Double> variances = list.stream().map(StepData::getVariance).filter(Objects::nonNull).collect(Collectors.toList());
        evaluationResult.setVariances(variances);
        evaluationResult.setStability(evaluate(variances));
        evaluationResult.setQuality(getQuality());
        try {
            FileUtils.writeStringToFile(file, JSONObject.toJSONString(evaluationResult), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.list.clear();
            this.inProcessOf = null;
        }
        return evaluationResult;
    }

    private File getFile() {
        String format = String.format("./data/%s/%s-%s/%s.%s", saveServer.getSn(), saveServer.getLayerNumber(), saveServer.getChannelNumber(), saveServer.getSn(), "json");
        return new File(format);
    }

    private String evaluate(List<Double> variances) {
        double collect = variances.stream().collect(Collectors.averagingDouble(Double::doubleValue));
        List<List<Object>> evaluateRule = systemConfig.getEvaluateRule();
        for (List<Object> objects : evaluateRule) {
            if (collect >= Double.parseDouble(objects.get(0).toString()) && collect < Double.parseDouble(objects.get(1).toString())) {
                return (String) objects.get(2);
            }
        }
        return "波动性大";
    }

    private String getQuality() {
        List<Error> errors = this.list.stream().flatMap(item -> item.getErrors().stream()).collect(Collectors.toList());
        Map<Integer, Long> collect = errors.stream().collect(Collectors.groupingBy(Error::getType, Collectors.counting()));
        if ((collect.containsKey(1) && collect.get(1) >= 5) ||
                (collect.containsKey(2) && collect.get(2) >= 1) ||
                (collect.containsKey(3) && collect.get(3) >= 1) ||
                (collect.containsKey(5) && collect.get(5) >= 1)
        ) {
            return "预警";
        }
        return "正常";
    }

}
