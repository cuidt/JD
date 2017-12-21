package com.example.lenovo.jd.model;

import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.Fenlei_bean;
import com.example.lenovo.jd.bean.Shouye_bean;
import com.example.lenovo.jd.listenter.OnfeileiFinishListener;
import com.example.lenovo.jd.listenter.OnshouyeFinishListener;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;



public class feileiBeanModellmpl implements fenleiBeanModel{
    @Override
    public void getData(final OnfeileiFinishListener listener) {
        RequestParams params=new RequestParams(API.FENLEI);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Fenlei_bean fenlei_bean = gson.fromJson(result, Fenlei_bean.class);
           if (listener!=null){
               listener.onSuccess(fenlei_bean);
           }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
