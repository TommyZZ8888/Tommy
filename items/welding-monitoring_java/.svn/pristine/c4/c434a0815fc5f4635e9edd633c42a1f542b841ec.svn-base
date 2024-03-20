package com.vren.weldingmonitoring_java.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * response 返回
 */
@Data
@ApiModel
public class ResponseResult<T> {

    @ApiModelProperty("状态码  1成功 0失败")
    protected Integer code;
    @ApiModelProperty("提示信息")
    protected String msg;
    @ApiModelProperty("返回的数据")
    protected T data;

    public ResponseResult() {
    }

    public ResponseResult(T data, String msg, Integer code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static <T> ResponseResult<T> success(String msg) {
        return new ResponseResult(true, msg, 1);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<>(data, msg, 1);
    }

    public static <T> ResponseResult<T> success(String msg, T data, Integer code) {
        return new ResponseResult<>(data, msg, code);
    }

    public static <T> ResponseResult<T> error(String msg) {
        return new ResponseResult(false, msg, 0);
    }

    public static <T> ResponseResult<T> error(String msg, T data) {
        return new ResponseResult<>(data, msg, 0);
    }

    public static <T> ResponseResult<T> error(String msg, T data, Integer code) {
        return new ResponseResult<>(data, msg, code);
    }
}
