package com.example.lenovo.jd.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.activity.ErweimaActivity;
import com.example.lenovo.jd.activity.SousuoActivity;
import com.example.lenovo.jd.activity.XqActivity;
import com.example.lenovo.jd.adapter.Myadapter;
import com.example.lenovo.jd.adapter.RvAdapter;
import com.example.lenovo.jd.adapter.TjAdapter;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.Shouye_bean;
import com.example.lenovo.jd.presenter.shouyeBeanPresenter;
import com.example.lenovo.jd.presenter.shouyeBeanPresenterlmpl;
import com.example.lenovo.jd.utils.GlideImageLaoder;
import com.example.lenovo.jd.utils.ToastUtils;
import com.example.lenovo.jd.view.UserBeanView;
import com.example.lenovo.jd.view.shouyeBeanView;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;



public class Fragmentshouye extends Fragment implements shouyeBeanView {
    private ImageView erweima;
    private TextView sousuo;
    private Banner banner;
    private ViewPager vp;
    private TextView ms_title;
    private TextView ms_time;
    private RecyclerView recycler_view;
    private RecyclerView tj_view;
    private ScrollView scrollView1;
    private SpringView springView;
    private ArrayList<Fragment> listf;
    private GridLayoutManager mLayoutManager;
    private GridLayoutManager tjLayoutManager;
    int REQUEST_CODE = 1;
    private ArrayList<String> urls;
    private ArrayList<String> titles;
    private ArrayList<String> imgs;
    private Shouye_bean shouye_bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View shouye = inflater.inflate(R.layout.fragmentshouye, container, false);
        shouyeBeanPresenterlmpl presenter = new shouyeBeanPresenterlmpl(this);
        presenter.relevance();
        initView(shouye);
        listf =new ArrayList<>();
        listf.add(new Fragment_1s());
        listf.add(new Fragment_2s());
        //秒杀
        //创建默认的线性LayoutManager
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        recycler_view.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setHasFixedSize(true);
        tjLayoutManager = new GridLayoutManager(getActivity(),2);
        tj_view.setLayoutManager(tjLayoutManager);
        tj_view.setHasFixedSize(true);
        Myadapter adapter =new Myadapter(getFragmentManager(),getActivity(),listf);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        //点击搜索关键字
        getCameraPermission();
        //ZXingLibrary初始化
        ZXingLibrary.initDisplayOpinion(getActivity());
        //点击二维码扫描
        erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        return shouye;
    }
    private void getCameraPermission() {
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA},2);
            }else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        }else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getActivity(),ErweimaActivity.class);
                    intent.putExtra("ewm",result);
                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private void initView(View shouye) {
        erweima = (ImageView) shouye.findViewById(R.id.erweima);
        sousuo = (TextView) shouye.findViewById(R.id.sousuo);
        banner = (Banner) shouye.findViewById(R.id.banner);
        vp = (ViewPager) shouye.findViewById(R.id.vp);
        ms_title = (TextView) shouye.findViewById(R.id.ms_title);
        ms_time = (TextView) shouye.findViewById(R.id.ms_time);
        recycler_view = (RecyclerView) shouye.findViewById(R.id.recycler_view);
        tj_view = (RecyclerView) shouye.findViewById(R.id.tj_view);
        scrollView1 = (ScrollView) shouye.findViewById(R.id.scrollView1);
        springView = (SpringView) shouye.findViewById(R.id.springView);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ToastUtils.showShortToast(getActivity(),"下拉刷新中");
                // list.clear();
             //    网络请求;
              //  mStarFragmentPresenter.queryData();
                //一分钟之后关闭刷新的方法
               finishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                ToastUtils.showShortToast(getActivity(),"玩命加载中...");
                finishFreshAndLoad();
            }
        });

        //DefaultHeader/Footer是SpringView已经实现的默认头/尾之一
        //更多还有MeituanHeader、AliHeader、RotationHeader等如上图7种
        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SousuoActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * 关闭加载提示
     */
    private void finishFreshAndLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                springView.onFinishFreshAndLoad();
            }
        }, 1000);
    }

    @Override
    public void showData(final Shouye_bean shouye_bean) {
        imgs = new ArrayList<>();
        titles = new ArrayList<>();
        urls = new ArrayList<>();
        List<Shouye_bean.DataBean> data = shouye_bean.getData();
        RvAdapter radapter= new RvAdapter(getActivity(), shouye_bean.getMiaosha().getList());
        TjAdapter tjadapter= new TjAdapter(getActivity(), shouye_bean.getTuijian().getList());

        recycler_view.setAdapter(radapter);
        radapter.setOnItemClickListener(new RvAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                ToastUtils.showShortToast(getActivity(), position+"条目");
                Intent intent=new Intent(getActivity(), XqActivity.class);
                intent.putExtra("pid", shouye_bean.getMiaosha().getList().get(position).getPid()+"");
                startActivity(intent);

            }
        });
        tj_view.setAdapter(tjadapter);
        tjadapter.setOnItemClickListener(new TjAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showShortToast(getActivity(), position+"条目");
                Intent intent=new Intent(getActivity(), XqActivity.class);
                intent.putExtra("pid", shouye_bean.getTuijian().getList().get(position).getPid()+"");
                startActivity(intent);
            }
        });
        for(int i=0;i<data.size();i++){
            imgs.add(data.get(i).getIcon());
            titles.add(data.get(i).getTitle());
            urls.add(data.get(i).getUrl());
        }
        //设置Banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLaoder());
        //实例化图片集合
        banner.setImages(imgs);
        //设置Banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //实例化Title集合
        //设置Banner标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //Banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String s = urls.get(position);
                ToastUtils.showShortToast(getActivity(),"轮播"+s);
            }
        });
    }
}