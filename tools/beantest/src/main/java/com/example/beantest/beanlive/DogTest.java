package com.example.beantest.beanlive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class DogTest {


    @Autowired
    private Dog dog;

    public Dog shout() {
        dog.name = "wangcai";
        return dog;
    }
}
