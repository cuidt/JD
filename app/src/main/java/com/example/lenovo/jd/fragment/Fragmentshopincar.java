package com.example.lenovo.jd.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.PayDemoActivity;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.activity.lijixiadanActivity;
import com.example.lenovo.jd.adapter.ShoppingCartAdapter;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.CarBean;
import com.example.lenovo.jd.bean.Test;
import com.example.lenovo.jd.presenter.CarBeanPresenterImpl;
import com.example.lenovo.jd.view.CarBeanView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Fragmentshopincar extends Fragment implements CarBeanView {

    private CarBeanPresenterImpl presenter;
    private String url = API.GOUWUCHE;
    private String uid;
    private LinearLayout clear;
    private TextView integral_sum;
    private TextView jiesuan;
    private ShoppingCartAdapter adapter;
    private int[] sumIntegral;
    private View view;
    private CheckBox checkBox_add;
    private CheckBox checkBox_select_all;
    private List<CarBean.DataBean> data1;
    private ListView mListView;
    private ArrayList<Test> data;
    private List<CarBean.DataBean.ListBean> list1;
    private String title;
    private double price;
    private String images;
    private boolean aboolean;
    private float price1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentshopingcar, container, false);
        presenter = new CarBeanPresenterImpl(this);
        uid=getActivity().getSharedPreferences("logUser", Context.MODE_PRIVATE).getString("uid", null);
            if(uid!=null){
                //p关联m 做网络请求
                presenter.relevance(url, uid);
            }
        return view;
    }
    @Override
    public void showData(CarBean carBean){
        data1 = carBean.getData();
        data = new ArrayList<Test>();
        for (int i = 0; i < data1.size(); i++) {
            list1 = data1.get(i).getList();
            for (int j = 0; j < list1.size(); j++) {
                title = list1.get(j).getTitle();
                price = list1.get(j).getPrice();
                images = list1.get(j).getImages();
            }
            System.out.println("price" + i + ":" + price);
            data.add(new Test(images, title, "无", "无", price + ""));
            mListView = (ListView) view.findViewById(R.id.finance_list);
            adapter = new ShoppingCartAdapter(getActivity(), handler, data);
            new Thread(){
                public void run() {

                    handler.post(runnableUi);
                }
            }.start();
            sumIntegral = new int[data.size() + 1];
            initView();
            adapter.notifyDataSetChanged();
        }
    }
    // 构建Runnable对象，在runnable中更新界面
    Runnable   runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
                mListView.setAdapter(adapter);
        }

    };
    private void initView() {
        jiesuan = (TextView)view.findViewById(R.id.jiesuan);
        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"正在结算",Toast.LENGTH_SHORT).show();
                 //data.clear();
                Intent intent = new Intent(getActivity(), lijixiadanActivity.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
                integral_sum.setText(0 + "");
                checkBox_select_all.setChecked(false);
                checkBox_add.setClickable(false);
            }
        });

        checkBox_add = (CheckBox) view.findViewById(R.id.checkbox_add);
        integral_sum = (TextView) view.findViewById(R.id.integral_sum);
        clear = (LinearLayout) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                data.clear();
                adapter.notifyDataSetChanged();
                integral_sum.setText(0 + "");
                checkBox_select_all.setChecked(false);
                checkBox_add.setClickable(false);
            }
        });
        checkBox_select_all = (CheckBox) view.findViewById(R.id.checkbox_select);
        checkBox_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Integer, Boolean> isSelected = ShoppingCartAdapter.getIsSelected();
                Iterator iterator = isSelected.entrySet().iterator();
                List<Boolean> array = new ArrayList<Boolean>();//列表中checkbox选中状态
                List<Integer> nums = new ArrayList<Integer>();//列表中商品数量

                while (iterator.hasNext()) {
                    HashMap.Entry entry = (HashMap.Entry) iterator.next();
                    Integer key = (Integer) entry.getKey();
                    Boolean val = (Boolean) entry.getValue();
                    array.add(val);
                }
                for (int i = 0; i < data.size(); i++) {
                    int num = data.get(i).getNum();
                    nums.add(num);
                }
                if (checkBox_select_all.isChecked()) {

                    for (int i = 0; i < data.size(); i++) {
                        ShoppingCartAdapter.getIsSelected().put(i, true);
                    }
                    checkBox_add.setChecked(true);
                    adapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        ShoppingCartAdapter.getIsSelected().put(i, false);
                    }
                    checkBox_add.setChecked(false);
                    adapter.notifyDataSetChanged();
                    integral_sum.setText(0 + "");
                }

            }
        });

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) { //更改选中商品的总价格
                price1 = (float) msg.obj;
                if (price1 > 0) {
                    integral_sum.setText(price1 + "");
                } else {
                    integral_sum.setText("0");
                }
            } else if (msg.what == 11) {

            }
        }
    };
}
