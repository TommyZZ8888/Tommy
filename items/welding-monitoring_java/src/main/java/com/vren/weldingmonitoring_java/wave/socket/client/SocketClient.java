package com.vren.weldingmonitoring_java.wave.socket.client;

import com.vren.weldingmonitoring_java.wave.socket.SocketConnectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public abstract class SocketClient implements ISocketClient {

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected boolean needStart = true;
    protected boolean hasStart = false;

    private Socket socket;

    public boolean onConnect() {
        return true;
    }

    public void onConnectAsync() {
    }

    protected final void connect() {
        synchronized (this) {
            boolean hasLog = false;
            while (true) {
                try {
                    socket = new Socket(this.getHost(), this.getPort());
                    log.info("Server {}:{} has connect success", this.getHost(), this.getPort());
                    if (needStart) needStart = this.onConnect();
                    if (!hasStart) {
                        hasStart = true;
                        SocketConnectPool.getExecutorService().execute(this::onConnectAsync);
                    }
                    this.notifyAll();
                    break;
                } catch (IOException e) {
                    if (!hasLog) {
                        log.error("Failed to connect to server {}:{} cause by {},Retry every 5 seconds...", this.getHost(), this.getPort(), e.getMessage());
                        hasLog = true;
                    }
                    try {
                        this.wait(5000L);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }
    }

    @PostConstruct
    public final void init() {
        SocketConnectPool.getExecutorService().execute(() -> {
            this.connect();
            String result;
            while (true) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    if ((result = bufferedReader.readLine()) == null) continue;
                    try {
                        this.onMessage(result);
                    } catch (Exception ignored) {
                    }
                } catch (IOException e) {
                    log.error("Client {}:{} {} Prepare for reconnection", this.getHost(), this.getPort(), e.getMessage());
                    try {
                        socket.close();
                    } catch (IOException ignored) {

                    }
                    this.socket = null;
                    this.connect();
                }
            }
        });
    }

    @Override
    public final void write(String message, boolean line) throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("Socket is closed");
        }
        StringBuilder msg = new StringBuilder();
        msg.append(message);
        if (line) {
            msg.append(System.lineSeparator());
        }
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(msg.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (SocketException e) {
            socket.close();
            socket = null;
        }
    }

    @Override
    public final void writeLine(String message) throws IOException {
        write(message, true);
    }

    public final Socket getSocket() {
        return this.socket;
    }

}
