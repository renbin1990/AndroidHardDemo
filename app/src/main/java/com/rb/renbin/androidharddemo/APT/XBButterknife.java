package com.rb.renbin.androidharddemo.APT;

import android.app.Activity;

/**
 * data:2021-09-07
 * Author:renbin
 */
public class XBButterknife {
    public static void bind(Activity activity){
        String name = activity.getClass().getName()+"_ViewBinding";
        try {
            Class<?> clazz = Class.forName(name);
            //因为MainActivity_ViewBinding实现的iBinder
            IBinder iBinder = (IBinder) clazz.newInstance();
            iBinder.bind(activity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
