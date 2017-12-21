package com.example.lenovo.jd.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.jd.PayDemoActivity;
import com.example.lenovo.jd.R;

import java.util.ArrayList;

public class lijixiadanActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_exit;
    private LinearLayout l1;
    private TextView name_phone;
    private TextView address;
    private RelativeLayout r1;
    private ImageView i1;
    private TextView t1_name;
    private TextView t1_price;
    private TextView t1_num;
    private LinearLayout l3;
    private LinearLayout l5;
    private TextView tv_gong;
    private LinearLayout l4;
    private TextView t2_zhifu;
    private TextView t2_zaixian;
    private TextView kuaidi;
    private TextView dayri;
    private LinearLayout l2;
    private TextView price;
    private Button btn_jiesuan;
    private RelativeLayout relvone;
    private View inflate;
    private Dialog dialog;
    private TextView choosePhoto;
    private TextView takePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijixiadan);
        initView();
    }

    private void initView() {
        image_exit = (ImageView) findViewById(R.id.image_exit);
        l1 = (LinearLayout) findViewById(R.id.l1);
        name_phone = (TextView) findViewById(R.id.name_phone);
        address = (TextView) findViewById(R.id.address);
        r1 = (RelativeLayout) findViewById(R.id.r1);
        i1 = (ImageView) findViewById(R.id.i1);
        t1_name = (TextView) findViewById(R.id.t1_name);
        t1_price = (TextView) findViewById(R.id.t1_price);
        t1_num = (TextView) findViewById(R.id.t1_num);
        l3 = (LinearLayout) findViewById(R.id.l3);
        t2_zhifu = (TextView) findViewById(R.id.t2_zhifu);
        t2_zaixian = (TextView) findViewById(R.id.t2_zaixian);
        kuaidi = (TextView) findViewById(R.id.kuaidi);
        dayri = (TextView) findViewById(R.id.dayri);
        l2 = (LinearLayout) findViewById(R.id.l2);
        price = (TextView) findViewById(R.id.price);
        btn_jiesuan = (Button) findViewById(R.id.btn_jiesuan);
        relvone = (RelativeLayout) findViewById(R.id.relvone);
        image_exit.setOnClickListener(this);
        btn_jiesuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jiesuan:
             //   startActivity(new Intent(this,PayDemoActivity.class));
                dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                inflate = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
                //初始化控件

                takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);
                choosePhoto.setOnClickListener(this);
                takePhoto.setOnClickListener(this);
                choosePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                takePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(lijixiadanActivity.this,PayDemoActivity.class));
                        finish();
                    }
                });
                //将布局设置给Dialog
                dialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity( Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
                break;
            case R.id.image_exit:
                finish();
                break;
        }
    }
}
