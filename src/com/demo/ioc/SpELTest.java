package com.demo.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Properties;

public class SpELTest {
    @Test
    public void helloWord() {
        //SpEL使用ExpressionParser接口表示解析器，提供SpelExpressionParser默认实现
        ExpressionParser parser = new SpelExpressionParser();
        //使用ExpressionParser的parseExpression来解析相应的表达式为Expression对象
        //parseExpression方法将字符串表达式转换成Expression对象
        Expression expression = parser.parseExpression("('hello'+' world').concat(#end)");
        //准备比如变量定义等表达式需要的上下文数据
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        //通过Expression接口的getValue方法根据上下文获得表达式值
        Assert.assertEquals("hello world!", expression.getValue(context));
        String str1 = parser.parseExpression("'Hello World!'").getValue(String.class);
        String str2 = parser.parseExpression("\"Hello World!\"").getValue(String.class);
        System.out.println(str2);
        System.out.println(str2);
    }

    @Test
    public void testParseContext() {
        ExpressionParser parser = new SpelExpressionParser();
        //ParserContext接口用于定义字符串是不是模板，及模板开始和结束字符
        //此处定义的表达式是模板，使用parseExpression解析时传入的模板必须以这里规定的前缀和后缀的形式传入
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "#{";
            }

            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        String template = "#{'Hello '}#{'World!'}";
        //Expression接口，表示表达式对象，提供getValue方法用于获取表达式的值，提供setValue方法设置对象值
        Expression expression = parser.parseExpression(template, parserContext);
        Assert.assertEquals("Hello World!", expression.getValue());
    }

    @Test
    public void testVariableExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("variable1", "haha");
//        context.setVariable("variable","haha");

        //使用#variable1来引用在EvaluationContext定义的变量
        //#后面的名称只要与EvaluationContext中定义的变量名一致即可，不一定是variable
        String result1 = parser.parseExpression("#variable1").getValue(context, String.class);
        Assert.assertEquals("haha", result1);

        context = new StandardEvaluationContext("hahaha");
        //还可以使用#root引用根对象
        String result2 = parser.parseExpression("#root").getValue(context, String.class);
        Assert.assertEquals("hahaha", result2);
        //使用#this引用当前上下文对象
        String result3 = parser.parseExpression("#this").getValue(context, String.class);
        Assert.assertEquals("hahaha", result3);

    }

    @Test
    public void testBeanExpression() {
        //初始化一个IOC容器
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
        ctx.refresh();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        //SpEL支持使用@来引用Bean，在引用Bean的时候需要使用BeanResolver接口实现来查找Bean
        context.setBeanResolver(new BeanFactoryResolver(ctx));

        //ClassPathXmlApplicationContext会默认把System.getProperties()注册为“systemProperties”Bean，因此在使用的时候可使用来引用该Bean
        Properties result1 = parser.parseExpression("@systemProperties").getValue(context, Properties.class);
        Assert.assertEquals(System.getProperties(), result1);
        System.out.println(result1);

    }

    @Test
    public void testXmlExpression(){
        ApplicationContext context=new ClassPathXmlApplicationContext("Bean.xml");
        String hello1=context.getBean("hello1",String.class);
        String hello4=context.getBean("hello4",String.class);
        String hello3=context.getBean("hello3",String.class);
        Assert.assertEquals("HelloWorld!",hello1);
        Assert.assertEquals("HelloWorld!",hello4);
        Assert.assertEquals("HelloWorld!",hello3);
    }

    /*执行不成功，可能是需要导入新的版本的aop的jar包*/
    @Test
    public void testPrefixExpression(){
        ApplicationContext context=new ClassPathXmlApplicationContext("SpEL.xml");
        SpELBean helloBean1=context.getBean("helloBean1",SpELBean.class);
        Assert.assertEquals("#{'Hello'+world}",helloBean1.getValue());
        SpELBean helloBean2=context.getBean("helloBean2",SpELBean.class);
        Assert.assertEquals("HelloWorld",helloBean2.getValue());
    }
}
