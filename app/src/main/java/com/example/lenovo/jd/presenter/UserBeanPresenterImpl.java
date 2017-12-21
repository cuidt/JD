package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.listenter.OnFinishListener;
import com.example.lenovo.jd.bean.UserBean;
import com.example.lenovo.jd.model.UserBeanModelImpl;
import com.example.lenovo.jd.view.UserBeanView;



public class UserBeanPresenterImpl implements UserBeanPresenter,OnFinishListener {
    UserBeanView userBeanView;
    private final UserBeanModelImpl userBeanModel;

    public UserBeanPresenterImpl(UserBeanView userBeanView) {
        this.userBeanView = userBeanView;
        userBeanModel = new UserBeanModelImpl();
    }
    @Override
    public void onSuccess(UserBean userBean,String str) {
           userBeanView.showData(userBean,str);
    }
    @Override
    public void relevance(String url,String mobile, String password) {
        userBeanModel.getData(this,url,mobile,password);
    }
}
