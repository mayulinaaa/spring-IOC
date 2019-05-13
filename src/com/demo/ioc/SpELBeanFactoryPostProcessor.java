package com.demo.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.expression.StandardBeanExpressionResolver;

public class SpELBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /*使用SpELBeanFactoryPostProcessor接口提供的postProcessBeanFactory回调方法，
    它是在IOC容器创建好但是还没进行任何Bean初始化时被ApplicationContext实现调用，
    因此在这个阶段把SpEL前缀及后缀修改掉是安全的*/
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        StandardBeanExpressionResolver resolver=(StandardBeanExpressionResolver) beanFactory.getBeanExpressionResolver();
        resolver.setExpressionPrefix("%{");
        resolver.setExpressionSuffix("}");
    }
}
