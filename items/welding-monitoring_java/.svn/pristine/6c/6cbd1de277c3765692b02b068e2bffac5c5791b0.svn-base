package com.vren.weldingmonitoring_java.common.domain;

import lombok.Data;

/**
 * response 返回
 */
@Data
public class ResponseDTO<T> {
    protected Integer code;
    protected String msg;
    protected T data;

    public ResponseDTO(T data, String msg, Integer code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static <T> ResponseDTO<T> success(String msg, T data) {
        return new ResponseDTO<>(data, msg, 1);
    }

    public static <T> ResponseDTO<T> error(String msg, T data) {
        return new ResponseDTO<>(data, msg, 0);
    }
}
