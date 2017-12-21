package com.example.lenovo.jd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.Picture.ChooseActivity;
import com.example.lenovo.jd.Picture.User;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.inter.ApiService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class updateNickName extends AppCompatActivity implements View.OnClickListener{

    private ImageView back1;
    private TextView queren;
    private EditText nicheng;
    private String nichengs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        initView();
    }

    private void initView() {
        back1 = (ImageView) findViewById(R.id.back1);
        queren = (TextView) findViewById(R.id.queren);
        nicheng = (EditText) findViewById(R.id.nicheng);
        back1.setOnClickListener(this);
        queren.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.queren:
                nichengs = nicheng.getText().toString();
                String uid = getSharedPreferences("logUser", Context.MODE_PRIVATE).getString("uid", null);
                getupdateNickName(nichengs,uid);
                break;
            case R.id.back1:
                finish();
                break;

        }
    }

    private void getupdateNickName(String s1, String uid1) {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.23.105/").addConverterFactory(GsonConverterFactory.create()).build();
        ////通过动态代理的方式得到网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        //创建RequestBody
        RequestBody uid = RequestBody.create(MediaType.parse("multipart/form-data"), uid1);
        RequestBody s = RequestBody.create(MediaType.parse("multipart/form-data"), s1);
        Call<User> call = apiService.updateNickName(uid, s);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                getSharedPreferences("logUser",MODE_PRIVATE).edit().putString("nickname",nichengs).commit();
                Toast.makeText(getBaseContext(), "昵称修改成功："+response.body().toString(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}
