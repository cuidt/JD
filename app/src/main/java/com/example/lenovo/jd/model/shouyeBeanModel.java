package com.example.lenovo.jd.model;
import com.example.lenovo.jd.listenter.OnFinishListener;
import com.example.lenovo.jd.listenter.OncarFinishListener;
import com.example.lenovo.jd.listenter.OnshouyeFinishListener;



public interface shouyeBeanModel {
    void getData(OnshouyeFinishListener listener);
}
