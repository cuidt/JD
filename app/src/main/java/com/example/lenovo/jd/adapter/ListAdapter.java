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
import com.example.lenovo.jd.bean.ListBean;
import java.util.List;
public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    public List<ListBean.DataBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
    public ListAdapter(List<ListBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gs_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.gstitle.setText(list.get(position).getTitle());
       viewHolder.gsprice.setText(list.get(position).getPrice()+"");
        Glide.with(context).load(list.get(position).getImages().split("\\|")[0]).into(viewHolder.gsimg);
        if(mOnItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(viewHolder.itemView,position); // 2
                }
            });
        }
        if(mOnItemClickListener!=null){
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int position = viewHolder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(viewHolder.itemView,position);
                    return true;
                }
            });
        }
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return list.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gstitle;
        public TextView gsprice;
        public ImageView gsimg;

        public ViewHolder(View view){
            super(view);
            gsprice = (TextView) view.findViewById(R.id.gs_price);
            gstitle = (TextView) view.findViewById(R.id.gs_title);
            gsimg = (ImageView) view.findViewById(R.id.gs_img);



        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }
}
