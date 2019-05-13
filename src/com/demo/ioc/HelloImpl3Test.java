package com.demo.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class HelloImpl3Test {
    @Test
    public void testConstructorDependencyInject(){
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("Bean.xml");
        HelloApi byIndex=beanFactory.getBean("byIndex",HelloApi.class);
        byIndex.sayHello();
        HelloApi byType=beanFactory.getBean("byType",HelloApi.class);
        byType.sayHello();
        HelloApi byName=beanFactory.getBean("byName",HelloApi.class);
        byName.sayHello();
        HelloApi setter=beanFactory.getBean("setter",HelloApi.class);
        setter.sayHello();
        HelloImpl4 listBean=beanFactory.getBean("listBean",HelloImpl4.class);
        System.out.println(listBean.getValues().size());
        //判断传入的两个值是否相同
        Assert.assertEquals(4,listBean.getValues().size());

        HelloImpl4 arrayBean=beanFactory.getBean("arrayBean",HelloImpl4.class);
        System.out.println(arrayBean.getArray2().length);
    }
}
