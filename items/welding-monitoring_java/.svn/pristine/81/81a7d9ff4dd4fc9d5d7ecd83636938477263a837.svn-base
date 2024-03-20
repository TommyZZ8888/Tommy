package com.vren.weldingmonitoring_java.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public abstract class FeignClientFactory {
    /**
     * 创建 feign 客户端
     *
     * @param clazz class
     * @param url   url
     * @param <T>   泛型参数
     * @return T
     */
    public static <T> T create(Class<T> clazz, String url) {
        // 1、定义 okHttpClient
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient()
                .newBuilder()
                .connectionPool(new ConnectionPool(5, 5L, TimeUnit.MINUTES))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        // 2、定义 feign 对应 OkHttpClient
        OkHttpClient client = new OkHttpClient(okHttpClient);
        FeignConvert feignConvert = new FeignConvert();
        // 3、创建 feign
        return Feign.builder()
                .client(client)
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .retryer(Retryer.NEVER_RETRY) // 重试机制
                .encoder(feignConvert)  // 自定义：入参编码器
                .decoder(feignConvert) // 定义：出参解码器
                .decode404() // 解码404，方便测试
                .target(clazz, url);
    }

    public static class FeignConvert implements Encoder, Decoder {

        @Override
        public void encode(Object object, Type bodyType, RequestTemplate template) {
            if (object != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString;
                try {
                    jsonString = objectMapper.writeValueAsString(object);
                    template.body(jsonString.getBytes(), StandardCharsets.UTF_8);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public Object decode(Response response, Type type) throws IOException, FeignException {
            Response.Body body = response.body();
            if (body == null) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(body.asInputStream(), TypeFactory.rawClass(type));
        }
    }

}

