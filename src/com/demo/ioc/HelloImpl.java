package com.demo.ioc;

public class HelloImpl implements HelloApi{

    @Override
    public void sayHello() {
        System.out.println("hello word!");
    }
}
