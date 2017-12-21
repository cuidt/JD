package com.example.lenovo.jd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.activity.SousuoActivity;
import com.example.lenovo.jd.adapter.FlAdapter;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.Fenlei_bean;
import com.example.lenovo.jd.presenter.fenleiBeanPresenterlmpl;
import com.example.lenovo.jd.utils.NoScrollViewPager;
import com.example.lenovo.jd.view.fenleiBeanView;
import com.example.lenovo.jd.view.shouyeBeanView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Lenovo on 2017/11/6.
 */
public class Fragmentfenlei extends Fragment implements fenleiBeanView {
    private View fenlei;
    private FlAdapter adapter;
    private int endIndex;
    private int beginIndex;
    private String str;
    private List<Fenlei_bean.DataBean> data;
    private List<Fenlei_bean.DataBean> cb;
    private NoScrollViewPager flvp;
    private Fragment[] frags;
    private ListView fl_list;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fenlei = inflater.inflate(R.layout.fragmentfenlei, container, false);
        initView();
        fenleiBeanPresenterlmpl presenterlmpl = new fenleiBeanPresenterlmpl(this);
        presenterlmpl.relevance();
        init();
        flvp.setCurrentItem(0);
        return fenlei;
    }
    public void initView(){
        TextView soso=fenlei.findViewById(R.id.soso);
        TextView egdsoso=fenlei.findViewById(R.id.egdsoso);
        egdsoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SousuoActivity.class);
                startActivity(intent);
            }
        });
        flvp= (NoScrollViewPager) fenlei.findViewById(R.id.flvp);
        //消除滑动效果
        flvp.setNoScroll(false);
        fl_list= (ListView) fenlei.findViewById(R.id.fl_list);
    }
    //条目点击切换viewpage 页面
    public void init(){
        fl_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flvp.setCurrentItem(position);
            }
        });
    }
    public void  getvp(final List<Fenlei_bean.DataBean> data){
        if(data!=null){
            frags = new Fragment[data.size()];
            cb = data;
        }
        flvp.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getfragment(position);
            }

            @Override
            public int getCount() {
                return data.size();
            }

        });

    }
    private Fragment getfragment(int position) {
        Fragment fg = frags[position];
        if (fg == null) {
            fg = TwoFragment.getiniturl(cb.get(position).getCid()+"");
            frags[position] = fg;
        }
        return fg;
    }

    @Override
    public void showData(Fenlei_bean fenlei_bean) {
        List<Fenlei_bean.DataBean> data = fenlei_bean.getData();
        FlAdapter adapter=new FlAdapter(getActivity(),data);
        fl_list.setAdapter(adapter);
        getvp(data);
    }
}
