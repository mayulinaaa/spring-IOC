package com.demo.ioc;

public class Singleton {
    //私有构造器
    private Singleton(){}
    //单例缓存者，惰性初始化，第一次使用的时候初始化
    private static class InstanceHolder{
        private static final Singleton INSTANCE=new Singleton();
    }
    //提供全局访问点
    public static Singleton getInstance(){
        return InstanceHolder.INSTANCE;
    }
    //提供一个计数器来验证一个ClassLoader实例
    private int counter=0;
}
