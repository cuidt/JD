package com.example.lenovo.jd.model;


import android.util.Log;

import com.example.lenovo.jd.listenter.OnFinishListener;
import com.example.lenovo.jd.bean.UserBean;
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


public class UserBeanModelImpl implements UserBeanModel{
    private OkHttpClient mOkHttpClient;

    @Override
    public void getData(final OnFinishListener listener, String url,String mobile, String password) {
        mOkHttpClient =new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("mobile", mobile)
                .add("password",password)
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
                str = response.body().string();
                beginIndex = str.indexOf("g")+4;
                endIndex = str.lastIndexOf("c")-3;
                String substring = str.substring(beginIndex, endIndex);
                Log.i("msg111", str.substring(beginIndex, endIndex));
                UserBean userBean = null;
                try {
                    Log.i("wangshu", str);
                    Gson gson=new Gson();
                    userBean = gson.fromJson(str, UserBean.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                if (listener!=null){
                        listener.onSuccess(userBean,substring);
                    }
            }

        });

    }
}
