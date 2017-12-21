package com.example.lenovo.jd.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.fragment.Fragmentfenlei;
import com.example.lenovo.jd.fragment.Fragmentfind;
import com.example.lenovo.jd.fragment.Fragmentgeren;
import com.example.lenovo.jd.fragment.Fragmentshopincar;
import com.example.lenovo.jd.fragment.Fragmentshouye;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton rb_shouye;
    private RadioButton rb_fenlei;
    private RadioButton rb_find;
    private RadioButton rb_shopingcar;
    private RadioButton rb_geren;
    private RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        show(new Fragmentshouye());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_shouye:
                show(new Fragmentshouye());
                break;
            case R.id.rb_fenlei:
                show(new Fragmentfenlei());
                break;
            case R.id.rb_find:
                show(new Fragmentfind());
                break;
            case R.id.rb_shopingcar:
                show(new Fragmentshopincar());
                break;
            case R.id.rb_geren:
                show(new Fragmentgeren());
                break;
        }


    }

    private void show(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
    }
    private void initView() {
        rb_shouye = (RadioButton) findViewById(R.id.rb_shouye);
        rb_fenlei = (RadioButton) findViewById(R.id.rb_fenlei);
        rb_find = (RadioButton) findViewById(R.id.rb_find);
        rb_shopingcar = (RadioButton) findViewById(R.id.rb_shopingcar);
        rb_geren = (RadioButton) findViewById(R.id.rb_geren);
        radio = (RadioGroup) findViewById(R.id.radio);
        rb_shouye.setOnClickListener(this);
        rb_fenlei.setOnClickListener(this);
        rb_find.setOnClickListener(this);
        rb_shopingcar.setOnClickListener(this);
        rb_geren.setOnClickListener(this);


    }
}
