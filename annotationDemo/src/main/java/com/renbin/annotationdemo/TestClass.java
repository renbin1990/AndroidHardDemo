package com.renbin.annotationdemo;

/**
 * data:2021-09-07
 * Author:renbin
 * 注解测试类
 */
class TestClass {

    @BindView(value = 20)
    private int a;
    @BindViewOne(id= 10,value = "20")
    private int b;

    @BindViewOne(id= 10,value ="10")
    public static void main(String[] args) {

    }
}
