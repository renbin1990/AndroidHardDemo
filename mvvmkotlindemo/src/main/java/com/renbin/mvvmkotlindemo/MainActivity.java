package com.renbin.mvvmkotlindemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.renbin.mvvmkotlindemo.databinding.ActivityMainBindingImpl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;

/**
 * data:2021-09-08
 * Author:renbin
 */
public class MainActivity extends AppCompatActivity implements DataBindingComponent {

    private ActivityMainBindingImpl mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        mBinding = new ActivityMainBindingImpl(this,view);
    }
}
