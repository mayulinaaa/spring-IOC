package com.demo.ioc;

public class HelloImpl2 implements HelloApi{
    private String message;
    public HelloImpl2(){
        this.message="hello word!";
    }
    public HelloImpl2(String mess){
        this.message=mess;
    }
    @Override
    public void sayHello() {
        System.out.println(message);
    }
}
