package com.vren.weldingmonitoring_java.domain.enums;

/**
 *
 * @author szp
 * @date 2023/6/30 15:53
 */

public enum CompletionStatus {
    NOT_STARTED(1,"未开始"),
    ONGOING(2,"进行中"),
    COMPLETED(3,"已完成");
    CompletionStatus(Integer code, String name){
        this.code = code;
        this.name = name;
    }
    public Integer getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    private Integer code;
    private String  name;

}
