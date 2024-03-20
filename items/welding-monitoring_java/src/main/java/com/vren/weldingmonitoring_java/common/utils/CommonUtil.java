package com.vren.weldingmonitoring_java.common.utils;

import java.util.List;

public class CommonUtil {

    /**
     *集合判空
     */
    public static Boolean listIsNotEmpty(List list) {
       if(list!=null && !list.isEmpty()){
           return true;
       }else {
           return false;
       }
    }

    /**
     * 对象判空
     */
    public static boolean isNull(Object object){
        if(null == object){
            return true;
        }
        if((object instanceof String)){
            return "".equals(((String)object).trim());
        }
        return false;
    }


}
