package com.example.lenovo.jd.fragment;

import android.content.Context;
import android.content.Intent;
import android.media.MediaDrm;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.activity.LoginActivity;
import com.example.lenovo.jd.activity.installActivity;
import com.example.lenovo.jd.bean.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class Fragmentgeren extends Fragment implements View.OnClickListener{
    private ImageView iv_login1;
    private TextView tv_login;
    private ImageView iv_set;
    private ImageView iv_news;
    private ImageView imageView;
    private TextView textView;
    private TextView tv_order;
    private TextView user_order_wait_pay;
    private TextView user_order_wait_drive;
    private TextView user_order_wait_receipt;
    private TextView user_order_wait_comment;
    private TextView user_order_wait_refund;
    private TextView user_property;
    private TextView user_property_money;
    private TextView user_property_card;
    private TextView user_property_vouchers;
    private TextView user_property_red;
    private TextView user_property_integral;
    private TextView user_address;
    private TextView user_setting;
    private LinearLayout dl2;
    private boolean yes;
    private String nickname;
    private String icon;
    private String msg1="1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View geren = inflater.inflate(R.layout.fragmentgeren, container, false);
        initView(geren);
        icon = getActivity().getSharedPreferences("logUser", Context.MODE_PRIVATE).getString("icon", null);
        nickname = getActivity().getSharedPreferences("logUser", Context.MODE_PRIVATE).getString("nickname", null);
        yes = getActivity().getSharedPreferences("logUser", Context.MODE_PRIVATE).getBoolean("yes", false);
       if (yes){
        tv_login.setText(nickname);
        Glide.with(getActivity()).load(icon).into(iv_login1);
           }
        return geren;
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        msg1 = event.getMsg();
        Log.d("harvic", msg1);
        Toast.makeText(getActivity(), msg1, Toast.LENGTH_LONG).show();
    }
    private void initView(View geren) {
        iv_login1 = (ImageView) geren.findViewById(R.id.iv_login1);
        tv_login = (TextView) geren.findViewById(R.id.tv_login);
        iv_set = (ImageView) geren.findViewById(R.id.iv_set);
        iv_news = (ImageView) geren.findViewById(R.id.iv_news);
        imageView = (ImageView) geren.findViewById(R.id.imageView);
        textView = (TextView) geren.findViewById(R.id.textView);
        tv_order = (TextView) geren.findViewById(R.id.tv_order);
        user_order_wait_pay = (TextView) geren.findViewById(R.id.user_order_wait_pay);
        user_order_wait_drive = (TextView) geren.findViewById(R.id.user_order_wait_drive);
        user_order_wait_receipt = (TextView) geren.findViewById(R.id.user_order_wait_receipt);
        user_order_wait_comment = (TextView) geren.findViewById(R.id.user_order_wait_comment);
        user_order_wait_refund = (TextView) geren.findViewById(R.id.user_order_wait_refund);
        user_property = (TextView) geren.findViewById(R.id.user_property);
        user_property_money = (TextView) geren.findViewById(R.id.user_property_money);
        user_property_card = (TextView) geren.findViewById(R.id.user_property_card);
        user_property_vouchers = (TextView) geren.findViewById(R.id.user_property_vouchers);
        user_property_red = (TextView) geren.findViewById(R.id.user_property_red);
        user_property_integral = (TextView) geren.findViewById(R.id.user_property_integral);
        user_address = (TextView) geren.findViewById(R.id.user_address);
        user_setting = (TextView) geren.findViewById(R.id.user_setting);
        dl2 = (LinearLayout) geren.findViewById(R.id.dl2);
        tv_login.setOnClickListener(this);
        iv_set.setOnClickListener(this);
        user_setting.setOnClickListener(this);
        tv_order.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login:
                getlogin();
            break;
            case R.id.iv_set:
                getlogin();
                break;
            case R.id.user_setting:
                getlogin();
                break;

            case R.id.tv_order:
               Toast.makeText(getActivity(),"全部订单", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getlogin() {
        if (!yes) {
            Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }else {
            startActivity(new Intent(getActivity(),installActivity.class));
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
