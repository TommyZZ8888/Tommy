package com.vren.weldingmonitoring_java.handler;

import com.vren.weldingmonitoring_java.common.domain.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局的异常处理
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult<?> exceptionHandler(Exception e, HttpServletResponse response, HttpServletRequest request) {
        log.error("error:", e);
        //http请求方式错误
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseResult.error("请求方式错误：" + e.getMessage());
        }

        //参数错误
        if (e instanceof TypeMismatchException) {
            return ResponseResult.error("参数异常：" + e.getMessage());
        }
        //json格式错误
        if (e instanceof HttpMessageNotReadableException) {
            return ResponseResult.error("JSON格式错误：" + e.getMessage());
        }
        //参数校验未通过
        if (e instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            List<String> msgList = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            return ResponseResult.error("参数校验错误：" + msgList.get(0));
        }
        if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            List<String> msgList = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return ResponseResult.error(msgList.get(0));
        }
        if (e instanceof NullPointerException) {
            return ResponseResult.error("空指针异常");
        }
        return ResponseResult.error(e.getMessage());
    }
}
