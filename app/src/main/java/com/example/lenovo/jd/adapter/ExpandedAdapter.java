package com.example.lenovo.jd.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.activity.DetailsActivity;
import com.example.lenovo.jd.activity.MainActivity;
import com.example.lenovo.jd.bean.ExBean;
import com.example.lenovo.jd.utils.ShowAllShopsType_list_grid;

import java.util.List;
public class ExpandedAdapter extends BaseExpandableListAdapter {
    private List<ExBean.DataBean> group;
    private List<List<ExBean.DataBean.ListBean>> child;
    private Context context;

    public ExpandedAdapter(List<ExBean.DataBean> group, List<List<ExBean.DataBean.ListBean>> child, Context context) {
        this.group = group;
        this.child = child;
        this.context = context;
    }
    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.group_item,null);
        TextView group_name= (TextView) view.findViewById(R.id.group_name);
        group_name.setText(group.get(groupPosition).getName());
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=View.inflate(context,R.layout.grald_item,null);
        final ShowAllShopsType_list_grid gv = view.findViewById(R.id.fl_gv);
        gv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return child.get(groupPosition).size();
            }

            @Override
            public Object getItem(int position) {
                return child.get(groupPosition).get(childPosition);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view=View.inflate(context, R.layout.child_item,null);
                TextView child_name= (TextView) view.findViewById(R.id.child_name);
                ImageView child_img= (ImageView) view.findViewById(R.id.child_img);

                child_name.setText(child.get(groupPosition).get(position).getName());
                Glide.with(context).load(child.get(groupPosition).get(position).getIcon()).into(child_img);

                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent=new Intent(context, DetailsActivity.class);
                        intent.putExtra("gscid",group.get(groupPosition).getList().get(position).getPscid()+"");
                        context.startActivity(intent);
                    }
                });

                return view;
            }

        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
