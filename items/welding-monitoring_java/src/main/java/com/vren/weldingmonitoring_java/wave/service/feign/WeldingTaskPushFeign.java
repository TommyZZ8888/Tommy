package com.vren.weldingmonitoring_java.wave.service.feign;

import com.vren.weldingmonitoring_java.common.domain.ResponseResult;
import com.vren.weldingmonitoring_java.wave.domain.vo.WeldingTaskMessage;
import feign.Headers;
import feign.RequestLine;

public interface WeldingTaskPushFeign {

    //起弧
    @RequestLine("POST /startCollect")
    @Headers("Content-Type: application/json; charset=utf-8")
    ResponseResult<Boolean> startCollect(WeldingTaskMessage weldingTaskMessage);

    //息弧
    @RequestLine("POST /stopCollect")
    @Headers("Content-Type: application/json; charset=utf-8")
    ResponseResult<Boolean> stopCollect(WeldingTaskMessage weldingTaskMessage);

    //旋转角度
    @RequestLine("POST /degree")
    @Headers("Content-Type: application/json; charset=utf-8")
    ResponseResult<Boolean> degree(WeldingTaskMessage weldingTaskMessage);

}
