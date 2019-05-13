package com.demo.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class AutowireBeanTest {
    @Test
    public void testAutowireByName(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("autowire.xml");
        HelloApi helloApi=context.getBean("bean",HelloApi.class);
        helloApi.sayHello();
    }
}
