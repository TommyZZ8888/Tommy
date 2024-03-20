package com.vren.weldingmonitoring_java.wave.socket;


import com.vren.common.common.utils.CustomThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketConnectPool {

    private static ExecutorService executorService;

    public static ExecutorService getExecutorService() {
        if (executorService == null) {
            synchronized (SocketConnectPool.class) {
                if (executorService != null) {
                    return executorService;
                }
                executorService = new ThreadPoolExecutor(
                        100, 200, 60, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(50000), CustomThreadFactory.create("SocketConnectPool"));
            }
        }
        return executorService;
    }

}
