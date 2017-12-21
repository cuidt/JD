package com.example.lenovo.jd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.bean.Shouye_bean;

import java.util.List;
public class RvAdapter  extends RecyclerView.Adapter<RvAdapter.ViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;
    public List<Shouye_bean.MiaoshaBean.ListBeanX> datas;
    private Context context;



    public RvAdapter(Context context,List<Shouye_bean.MiaoshaBean.ListBeanX> datas) {
        this.datas = datas;
        this.context = context;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recler_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.ms_price.setText(datas.get(position).getPrice()+"");
        String[] split = datas.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.ms_img);
        //将position保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ms_price;
        public ImageView ms_img;
        public ViewHolder(View view){
            super(view);
            ms_price = (TextView) view.findViewById(R.id.ms_price);
            ms_img = (ImageView) view.findViewById(R.id.ms_img);
        }
    }


    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
