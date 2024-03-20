package com.vren.weldingmonitoring_java.wave.socket.server;

import com.vren.weldingmonitoring_java.wave.socket.SocketConnectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public abstract class SocketServer implements ISocketServer {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ServerSocket serverSocket;

    protected ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void onServerStart(ServerSocket serverSocket) {
    }

    public void onClientConnect(Socket socket) {
    }

    public void onClientDisconnect(Socket socket) {
    }

    public final void write(Socket socket, String message, boolean line) throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("Client is closed");
        }
        StringBuilder msg = new StringBuilder();
        msg.append(message);
        if (line) {
            msg.append(System.lineSeparator());
        }
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(msg.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    public final void writeLine(Socket socket, String message) throws IOException {
        write(socket, message, true);
    }

    @PostConstruct
    public final void init() {
        try {
            serverSocket = new ServerSocket(this.getPort());
            onServerStart(serverSocket);
            log.info("Port {} service initialization success", this.getPort());
            SocketConnectPool.getExecutorService().execute(this::serverHandler);
        } catch (IOException e) {
            log.error("Port {} service initialization failed cause by {}", this.getPort(), e.getMessage());
        }
    }

    protected final void serverHandler() {
        while (true) {
            try {
                Socket accept = serverSocket.accept();
                log.info("There is a client joining the connection：{}", accept.getRemoteSocketAddress());
                this.onClientConnect(accept);
                SocketConnectPool.getExecutorService().execute(() -> this.clientHandler(accept));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected final void clientHandler(Socket accept) {
        try {
            InputStream inputStream = accept.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String result;
            while ((result = bufferedReader.readLine()) != null) {
                try {
                    this.onMessage(accept, result);
                } catch (Exception ignored) {
                }
            }
            accept.close();
            this.onClientDisconnect(accept);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
