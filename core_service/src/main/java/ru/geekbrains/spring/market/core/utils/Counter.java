package ru.geekbrains.spring.market.core.utils;

import org.springframework.stereotype.Component;

@Component
public class Counter {

    private int value;

    public void inc(){
        value++;
    }

    public void dec(){
        value--;
    }

    public int getValue(){
        return value;
    }


}
