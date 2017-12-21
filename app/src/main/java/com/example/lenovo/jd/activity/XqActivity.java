package com.example.lenovo.jd.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.adapter.ListAdapter;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.BaseBean;
import com.example.lenovo.jd.bean.ListBean;
import com.example.lenovo.jd.bean.XqBean;

import com.example.lenovo.jd.utils.GlideImageLaoder;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class XqActivity extends AppCompatActivity {
    private String uid;
    private List<String> mListImage;
    private List<String> mListTitle;
    private String gscid="1";
    private String url= API.XQ;
    private String pid;
    private RadioButton xq_lx;
    private RadioButton xq_sj;
    private RadioButton xq_dp;
    private TextView xq_addcar;
    private Banner xq_banner;
    private TextView xq_title;
    private TextView xq_price;
    private TextView xq_subhead;
    private ImageView xq_back;
    private ImageView xq_fx;
    private RecyclerView xq_RecyclerView;
    private int t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        initView();
        getData();
        getdata();

        xq_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("pid",pid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(result!=null){
                    final Gson gson=new Gson();
                    final XqBean bean = gson.fromJson(result, XqBean.class);
                    final XqBean.DataBean data = bean.getData();
                    gscid=data.getPscid()+"";
                    mListImage=new ArrayList<String>();
                    for(int i=0;i<bean.getData().getImages().split("\\|").length;i++){
                    mListImage.add(bean.getData().getImages().split("\\|")[i]);
                        }
                    initbanner();
                    xq_title.setText(bean.getData().getTitle());
                    xq_price.setText("$$"+bean.getData().getPrice()+"");
                    xq_subhead.setText(bean.getData().getSubhead());

                    xq_sj.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(XqActivity.this,bean.getSeller().getName(),Toast.LENGTH_LONG).show();
                        }
                    });
                    //点击添加购物车
                    xq_addcar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //得到判断登录的sp
                            SharedPreferences pref = getSharedPreferences("logUser",MODE_PRIVATE);
                            boolean yes = pref.getBoolean("yes", false);
                            uid = pref.getString("uid", null);
                           //判断是否登录
                            if(yes){
                                RequestParams params=new RequestParams(API.ADDCAR);
                                params.addBodyParameter("uid",uid);
                                params.addBodyParameter("pid",data.getPid()+"");
                                params.addBodyParameter("token","58231D3E963D88765BB0130473BD3636");
                                x.http().post(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        if(result!=null){
                                            Gson gson=new Gson();
                                            BaseBean bb = gson.fromJson(result, BaseBean.class);
                                            Toast.makeText(XqActivity.this,bb.msg,Toast.LENGTH_LONG).show();

                                        }
                                    }
                                    @Override
                                    public void onError(Throwable ex, boolean isOnCallback) {
                                    }
                                    @Override
                                    public void onCancelled(CancelledException cex) {
                                    }
                                    @Override
                                    public void onFinished() {
                                    }
                                });
                            }
                           else{
                                Toast.makeText(XqActivity.this,"请先登录",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
    //初始化控件
    public void initView(){
         xq_lx= (RadioButton) findViewById(R.id.xq_lx);
         xq_sj= (RadioButton) findViewById(R.id.xq_sj);
         xq_dp= (RadioButton) findViewById(R.id.xq_dp);
         xq_addcar= (TextView) findViewById(R.id.xq_addcar);
         xq_banner= (Banner) findViewById(R.id.xq_banner);
         xq_title= (TextView) findViewById(R.id.xq_title);
         xq_price= (TextView) findViewById(R.id.xq_price);
         xq_subhead= (TextView) findViewById(R.id.xq_subhead);
        xq_RecyclerView= (RecyclerView) findViewById(R.id.xq_RecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(XqActivity.this);
        xq_RecyclerView.setLayoutManager(mLayoutManager);
        xq_RecyclerView.setHasFixedSize(true);
        xq_back= (ImageView) findViewById(R.id.xq_back);
        xq_fx= (ImageView) findViewById(R.id.xq_fx);
    }
    //设置banner样式
    public void initbanner(){
        xq_banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        xq_banner.setImageLoader(new GlideImageLaoder());
        xq_banner.setImages(mListImage);
        //设置Banner动画效果
        xq_banner.setBannerAnimation(Transformer.DepthPage);
        mListTitle=new ArrayList<>();
        xq_banner.isAutoPlay(false);
        //设置轮播时间
        xq_banner.setDelayTime(1000);
        //设置指示器位置（当banner模式中有指示器时）
        xq_banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //Banner设置方法全部调用完毕时最后调用
        xq_banner.start();
    }
    public void getdata(){
        RequestParams params=new RequestParams(API.SPLIST);
        params.addBodyParameter("pscid",gscid);
        params.addBodyParameter("page","1");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                final ListBean listBean = gson.fromJson(result, ListBean.class);
                ListAdapter   adapter=new ListAdapter(listBean.getData(),XqActivity.this);
                xq_RecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent=new Intent(XqActivity.this,XqActivity.class);
                        intent.putExtra("pid",listBean.getData().get(position).getPid()+"");
                        startActivity(intent);
                        finish();
                    }
                });
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }




}
