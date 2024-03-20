package com.vren.weldingmonitoring_java.wave.socket.client;


import java.io.IOException;

public interface ISocketClient {
    /**
     * 连接成功时调用的方法
     * 返回值表示重连时是否再次调用
     */
    boolean onConnect();

    /**
     * 连接成功时另起一个线程调用的方法
     * 只会调用一次
     */
    void onConnectAsync();

    /**
     * 远程服务的地址
     *
     * @return
     */
    String getHost();

    /**
     * 远程服务的端口
     *
     * @return
     */
    Integer getPort();

    /**
     * 向远程服务发送消息
     *
     * @param message
     * @param line
     * @throws IOException
     */
    void write(String message, boolean line) throws IOException;

    /**
     * 向远程服务发送消息
     *
     * @param message
     * @throws IOException
     */
    void writeLine(String message) throws IOException;

    /**
     * 接收到远程服务的消息
     *
     * @param message
     */
    void onMessage(Object message);

}
