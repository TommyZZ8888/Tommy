package com.vren.weldingmonitoring_java.wave.socket.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public interface ISocketServer {
    /**
     * 服务注册端口
     *
     * @return Integer
     */
    Integer getPort();

    /**
     * 接受来自客户端的消息
     *
     * @param socket
     * @param message
     */
    void onMessage(Socket socket, String message);

    /**
     *  服务启动
     * @param serverSocket
     */
    void onServerStart(ServerSocket serverSocket);

    /**
     * 有客户端加入
     *
     * @param socket
     */
    void onClientConnect(Socket socket);

    /**
     * 有客户端断开连接
     *
     * @param socket
     */
    void onClientDisconnect(Socket socket);

    /**
     * 向客户端发送消息
     *
     * @param socket
     * @param message
     * @param line
     * @throws IOException
     */
    void write(Socket socket, String message, boolean line) throws IOException;

    /**
     * 向客户端发送消息
     *
     * @param socket
     * @param message
     * @throws IOException
     */
    void writeLine(Socket socket, String message) throws IOException;

}
