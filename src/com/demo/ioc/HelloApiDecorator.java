package com.demo.ioc;

public class HelloApiDecorator implements HelloApi{
    private HelloApi helloApi;
    //空的构造器
    public HelloApiDecorator(){}
    //有参构造器
    public HelloApiDecorator(HelloApi helloApi){
        this.helloApi=helloApi;
    }

    public void setHelloApi(HelloApi helloApi) {
        this.helloApi = helloApi;
    }

    @Override
    public void sayHello() {
        System.out.println("装饰一下");
        helloApi.sayHello();
        System.out.println("装饰一下");
    }
}
