package com.example.lenovo.jd.presenter;

import com.example.lenovo.jd.bean.Fenlei_bean;
import com.example.lenovo.jd.bean.Shouye_bean;
import com.example.lenovo.jd.listenter.OnfeileiFinishListener;
import com.example.lenovo.jd.listenter.OnshouyeFinishListener;
import com.example.lenovo.jd.model.feileiBeanModellmpl;
import com.example.lenovo.jd.model.shouyeBeanModel;
import com.example.lenovo.jd.model.shouyeBeanModellmpl;
import com.example.lenovo.jd.view.fenleiBeanView;
import com.example.lenovo.jd.view.shouyeBeanView;


public class fenleiBeanPresenterlmpl implements fenleiBeanPresenter,OnfeileiFinishListener {
    fenleiBeanView fenleiBeanView;
    private final feileiBeanModellmpl fenleiBeanModel;

    public fenleiBeanPresenterlmpl(fenleiBeanView fenleiBeanView) {
        this.fenleiBeanView = fenleiBeanView;
        fenleiBeanModel = new feileiBeanModellmpl();
    }
    @Override
    public void onSuccess(Fenlei_bean fenlei_bean) {
        fenleiBeanView.showData(fenlei_bean);
    }
    @Override
    public void relevance() {
        fenleiBeanModel.getData(this);
    }
}
