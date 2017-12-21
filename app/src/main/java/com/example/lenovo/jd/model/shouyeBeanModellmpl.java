package com.example.lenovo.jd.model;

import android.content.Intent;
import android.view.View;

import com.example.lenovo.jd.activity.XqActivity;
import com.example.lenovo.jd.adapter.RvAdapter;
import com.example.lenovo.jd.adapter.TjAdapter;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.Shouye_bean;
import com.example.lenovo.jd.listenter.OnFinishListener;
import com.example.lenovo.jd.listenter.OnshouyeFinishListener;
import com.example.lenovo.jd.utils.GlideImageLaoder;
import com.example.lenovo.jd.utils.ToastUtils;
import com.google.gson.Gson;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;



public class shouyeBeanModellmpl implements shouyeBeanModel{
    @Override
    public void getData(final OnshouyeFinishListener listener) {
        //网络请求设置轮播图
        final RequestParams params=new RequestParams(API.SHOUYE);
        x.http().get(params, new Callback.CommonCallback<String>() {
            private Shouye_bean shouye_bean;
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                shouye_bean = gson.fromJson(result, Shouye_bean.class);
                if (listener!=null){
                    listener.onSuccess(shouye_bean);
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
