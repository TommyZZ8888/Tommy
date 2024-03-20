package com.vren.weldingmonitoring_java;

import com.vren.weldingmonitoring_java.exception.ErrorException;
import lombok.extern.slf4j.Slf4j;

import java.rmi.server.ExportException;

@Slf4j
public class Tests {


    public static void main(String[] args) {
        try {
            try {
                throw new ExportException("test");
            } catch (ErrorException errorException) {
                throw errorException;
            } catch (Exception e) {
                log.info("内：{}", e.getMessage());
            }
        } catch (ErrorException e) {
            log.info("外：{}", e.getMessage());
        }
    }

}
