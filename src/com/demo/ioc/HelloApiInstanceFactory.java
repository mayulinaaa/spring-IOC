package com.demo.ioc;

public class HelloApiInstanceFactory {
    public HelloApi newInstanceFactory(String message){
        return new HelloImpl2(message);
    }
}
