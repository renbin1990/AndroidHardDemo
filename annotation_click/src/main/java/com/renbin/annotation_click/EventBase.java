package com.renbin.annotation_click;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * data:2021-09-07
 * Author:renbin
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    //1.setOnClickListener  订阅关系
    String listenerSetter();
    //2.new View.OnClickListener()  事件本身
    Class<?> listenerType();
    //3.事件处理程序  onClick方法
    String callbackMethod();
}
