package com.demo.ioc;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class IOCTest {
    @Test
    public void textUser(){
        /*//读取配置文件实例化一个IOC容器
        ApplicationContext context=new ClassPathXmlApplicationContext("Bean.xml");
        //从容器中获取对象
        Person person=(Person)context.getBean("person");
        System.out.println(person);
        System.out.println(person.getName());
        System.out.println(person.getAge());*/

        /*BeanFactory beanFactory=new ClassPathXmlApplicationContext("Bean.xml");
        //根据id获取bean
        Person person1=beanFactory.getBean("person",Person.class);
        //根据别名获取bean
        Person person2=beanFactory.getBean("alias1",Person.class);
        //获取该bean的别名，如果id和name一样，IOC能检测到并且消除冲突
        String[] alias=beanFactory.getAliases("person");
        System.out.println(person1.getName());
        System.out.println(person2.getAge());
        for (String aliasName:alias) {
            System.out.println(aliasName);
        }*/

        /*
//        读取配置文件，实例化一个IOC容器
        ApplicationContext context=new ClassPathXmlApplicationContext("Bean.xml");
//        从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
        HelloApi helloApi=context.getBean("hello",HelloApi.class);
        helloApi.sayHello();*/
        //ApplicationContext实现，从classpath中获取配置文件
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("Bean.xml");
        HelloApi bean1=beanFactory.getBean("bean1",HelloApi.class);
        bean1.sayHello();
        HelloApi bean2=beanFactory.getBean("bean2",HelloApi.class);
        bean2.sayHello();
        HelloApi bean3=beanFactory.getBean("bean3",HelloApi.class);
        bean3.sayHello();
    }
    @Test
    public void testLocalAndParentBeanInject(){
        //初始化父容器
        ApplicationContext parentBeanContext=new ClassPathXmlApplicationContext("parentBeanInject");
        //初始化当前容器
        ApplicationContext beanContext=new ClassPathXmlApplicationContext(new String[]{"BeanInject.xml"},parentBeanContext);
        HelloApi bean1=beanContext.getBean("bean1",HelloApi.class);
        bean1.sayHello();
        HelloApi bean2=beanContext.getBean("bean2",HelloApi.class);
        bean2.sayHello();
    }
}
