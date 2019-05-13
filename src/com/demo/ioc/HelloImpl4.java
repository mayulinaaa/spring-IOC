package com.demo.ioc;

import java.util.List;

public class HelloImpl4 implements HelloApi{
    private int index;
    private String message;
    //spring还可以注入集合类
    private List<String> values;
    //spring还可以注入数组
    private String[] array1;
    private String[][] array2;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String[] getArray1() {
        return array1;
    }

    public void setArray1(String[] array1) {
        this.array1 = array1;
    }

    public String[][] getArray2() {
        return array2;
    }

    public void setArray2(String[][] array2) {
        this.array2 = array2;
    }

    @Override
    public void sayHello() {
        System.out.println(index+":"+message);
    }
}
