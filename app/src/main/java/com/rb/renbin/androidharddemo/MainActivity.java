package com.rb.renbin.androidharddemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rb.renbin.androidharddemo.APT.XBButterknife;
import com.renbin.annotation_click.InjectUtils;
import com.renbin.annotation_click.OnClick;
import com.renbin.annotation_click.OnLongClick;
import com.renbin.annotationdemo.BindView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化ART找控件
        XBButterknife.bind(this);
        //初始化控件点击
        InjectUtils.inject(this);

        btn1.setText("btn1");
        btn2.setText("btn2");

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "1111", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @OnClick({R.id.btn1,R.id.btn2})
    public void onClick(View view){
        Toast.makeText(MainActivity.this, "单次点击", Toast.LENGTH_SHORT).show();
    }
    @OnLongClick({R.id.btn2})
    public boolean onLongClick(View view){
        Toast.makeText(MainActivity.this, "长按点击", Toast.LENGTH_SHORT).show();
        return false;
    }

}