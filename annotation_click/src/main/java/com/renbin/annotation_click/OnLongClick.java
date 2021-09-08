package com.renbin.annotation_click;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * data:2021-09-07
 * Author:renbin
 */
@EventBase(listenerSetter = "setOnLongClickListener"
        ,listenerType = View.OnLongClickListener.class
        ,callbackMethod = "onLongClick")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnLongClick {
    int[] value() default -1;
}

