package com.renbin.mvvmkotlindemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * data:2021-09-08
 * Author:renbin
 */
public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<String> info;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getInfo() {
        if (info == null){
            info = new MutableLiveData<>();
            info.setValue("110");
        }
        return info;
    }

    public void appendNumber(String number){
        info.setValue(info.getValue()+number);
    }

    public void deleteNumber(String number){
        int length = info.getValue().length();
        if (length > 0) {
            info.setValue(info.getValue().substring(0,info.getValue().length()-1));
        }
    }

    public void clear(){
        info.setValue("");
    }
}
