package com.vren.weldingmonitoring_java.utils;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchUtil {
    public static <T> void timeRangeSearch(MPJLambdaWrapper<T> wrapper, SFunction<T, ?> column, Date createStartTime, Date createEndTime) {
        if (createStartTime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            wrapper.ge(column, simpleDateFormat.format(createStartTime));
        }
        if (createEndTime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            wrapper.le(column, simpleDateFormat.format(createEndTime));
        }
    }
}
