package com.example.lenovo.jd.model;


import android.util.Log;

import com.example.lenovo.jd.bean.CarBean;
import com.example.lenovo.jd.bean.UserBean;
import com.example.lenovo.jd.listenter.OnFinishListener;
import com.example.lenovo.jd.listenter.OncarFinishListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class CarBeanModelImpl implements CarBeanModel{

    private OkHttpClient mOkHttpClient;

    @Override
    public void getData(final OncarFinishListener listener, String url, String uid) {
        mOkHttpClient =new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("uid", uid)
                .add("token","1")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {

            private int endIndex;
            private int beginIndex;
            private String str;

            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                str = response.body().string();;

                    Log.i("wangshu", str);


                            Gson gson=new Gson();
                            CarBean carBean = gson.fromJson(str, CarBean.class);
                            if(carBean.getMsg().equals("请求成功")){
                            if (listener!=null){
                                listener.onSuccess(carBean);
                            }
                        }

            }

        });
    }
}
