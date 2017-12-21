package com.example.lenovo.jd.model;
import com.example.lenovo.jd.listenter.OncarFinishListener;



public interface CarBeanModel {
    void getData(OncarFinishListener listener,String url,String uid);
}
