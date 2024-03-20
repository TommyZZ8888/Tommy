package com.vren.weldingmonitoring_java;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vren.weldingmonitoring_java.exception.ErrorException;
import com.vren.weldingmonitoring_java.socket.server.ImageBody;
import com.vren.weldingmonitoring_java.wave.socket.client.SocketClient;
import com.vren.weldingmonitoring_java.wave.socket.server.SocketServer;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
public class DataSimulation {


    public static void main(String[] args) {
//        ImageData imageData = new ImageData();
//        new Thread(imageData::init).start();

        ErrorData errorData = new ErrorData();
        new Thread(errorData::init).start();
//        new IUData().init();
        while (true) {
        }
    }


    static class IUData extends SocketServer {
        @Override
        public void onMessage(Socket socket, String message) {
            JSONObject jsonObject = new JSONObject();
            List<Double> weldIs = new ArrayList<>();
            List<Double> arcUs = new ArrayList<>();
            for (int i = 0; i < 170; i++) {
                weldIs.add(Double.valueOf(String.format("%.2f", 100 + (Math.random() * (3 + 3 + 1) - 3))));
                arcUs.add(Double.valueOf(String.format("%.2f", RandomUtil.randomDouble(13.00, 26.00))));
            }
            jsonObject.put("WeldIs", weldIs);
            jsonObject.put("ArcUs", arcUs);
            try {
                writeLine(socket, jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Integer getPort() {
            return 8088;
        }
    }

    static class ImageData extends SocketClient {
        @Override
        public String getHost() {
            return "127.0.0.1";
        }

        @Override
        public Integer getPort() {
            return 7777;
        }

        @Override
        public void onConnectAsync() {
            Base64.Encoder encoder = Base64.getEncoder();
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber("C:\\Users\\Lenovo\\Desktop\\test.mp4");
            while (true) {
                try {
                    fFmpegFrameGrabber.restart();
                    int length = fFmpegFrameGrabber.getLengthInFrames();
                    Frame frame;
                    for (int i = 0; i < length; i++) {
                        frame = fFmpegFrameGrabber.grabFrame();
                        if (frame.image == null) {
                            continue;
                        }
                        Java2DFrameConverter converter = new Java2DFrameConverter();
                        BufferedImage image = converter.getBufferedImage(frame);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        ImageIO.write(image, "png", stream);
                        byte[] encode = encoder.encode(stream.toByteArray());
                        ImageBody imageBody = new ImageBody();
                        imageBody.setPicture(new String(encode));
                        imageBody.setCameraSN("sn23154058");
                        writeLine(JSON.toJSONString(imageBody));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onMessage(Object message) {

        }
    }

    static class ErrorData extends SocketServer {

        @Override
        public void onMessage(Socket socket, String message) {
            throw new ErrorException("test");
        }

        @Override
        public Integer getPort() {
            return 8089;
        }
    }
}
