package com.example.beantest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.example")
public class BeantestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeantestApplication.class, args);
    }

}
