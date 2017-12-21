package com.example.lenovo.jd.presenter;


import com.example.lenovo.jd.bean.CarBean;
import com.example.lenovo.jd.bean.UserBean;
import com.example.lenovo.jd.listenter.OncarFinishListener;
import com.example.lenovo.jd.model.CarBeanModelImpl;
import com.example.lenovo.jd.view.CarBeanView;



public class CarBeanPresenterImpl implements CarBeanPresenter,OncarFinishListener {
    CarBeanView carBeanView;
    private final CarBeanModelImpl carBeanModel;

    public CarBeanPresenterImpl(CarBeanView carBeanView) {
        this.carBeanView = carBeanView;
        carBeanModel = new CarBeanModelImpl();
    }
    @Override
    public void onSuccess(CarBean carBean) {
        carBeanView.showData(carBean);
    }
    @Override
    public void relevance(String url, String uid) {
        carBeanModel.getData(this,url,uid);
    }

}
