package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.bean.Shouye_bean;
import com.example.lenovo.jd.listenter.OnshouyeFinishListener;
import com.example.lenovo.jd.model.shouyeBeanModellmpl;
import com.example.lenovo.jd.view.UserBeanView;
import com.example.lenovo.jd.view.shouyeBeanView;



public class shouyeBeanPresenterlmpl implements shouyeBeanPresenter,OnshouyeFinishListener{
    shouyeBeanView shouyeBeanView;
    private final shouyeBeanModellmpl shouyeBeanModel;

    public shouyeBeanPresenterlmpl(shouyeBeanView shouyeBeanView) {
        this.shouyeBeanView = shouyeBeanView;
        shouyeBeanModel = new shouyeBeanModellmpl();
    }
    @Override
    public void onSuccess(Shouye_bean shouye_bean) {
        shouyeBeanView.showData(shouye_bean);
    }
    @Override
    public void relevance() {
        shouyeBeanModel.getData(this);
    }
}
