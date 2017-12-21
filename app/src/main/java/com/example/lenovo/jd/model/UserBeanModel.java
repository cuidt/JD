package com.example.lenovo.jd.model;

import com.example.lenovo.jd.listenter.OnFinishListener;



public interface UserBeanModel {
    void getData(OnFinishListener listener,String url,String mobile, String password);
}
