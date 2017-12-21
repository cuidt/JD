package com.example.lenovo.jd.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.dzbean;
import com.example.lenovo.jd.bean.dzlistbean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class cydzActivity extends AppCompatActivity {

    private ListView list_cy;
    private String uid;
    private List<dzlistbean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cydz);
        initView();
        uid = getSharedPreferences("logUser",MODE_PRIVATE).getString("uid", null);
        getdata();

        list_cy.setAdapter(new BaseAdapter() {

            private viewholder holder;

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(cydzActivity.this, R.layout.item, null);
                    holder = new viewholder();
                    holder.name = convertView.findViewById(R.id.text);
                    holder.title = convertView.findViewById(R.id.textname);
                    convertView.setTag(holder);
                } else {
                    holder = (viewholder) convertView.getTag();
                }
                holder.name.setText(data.get(position).getAddr());
                holder.title.setText(data.get(position).getMobile());
                return convertView;
            }

            class viewholder {
                TextView name;
                TextView title;
            }
        });
    }
    private void getdata() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("uid", uid)
                .build();
        Request request = new Request.Builder()
                .url(API.cydizhi)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(cydzActivity.this,"onFailure", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Gson gson = new Gson();
                dzlistbean dzlistbean = gson.fromJson(string, dzlistbean.class);
                data = dzlistbean.getData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(cydzActivity.this, "onResponse"+data, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

    private void initView() {
        list_cy = (ListView) findViewById(R.id.list_cy);
    }
}
