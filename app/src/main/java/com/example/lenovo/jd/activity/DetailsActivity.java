package com.example.lenovo.jd.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.adapter.ListAdapter;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.ListBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{
    //商品列表页
    private TextView aaa;
    private int i = 1;
    private Boolean isCheck = false;
    private String page = i + "";
    private String url = API.SPLIST;
    private String pscid;
    private ListAdapter adapter;
    private RadioButton horver;
    private RecyclerView listRecyclerView;
    private TextView sort_price;
    private EditText soso;
    private RecyclerView list_RecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        pscid = intent.getStringExtra("gscid");
        initView();
        horver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheck = !isCheck;
                horver.setChecked(!isCheck);
                if (horver.isChecked()) {
                    GridLayoutManager mLayoutManager = new GridLayoutManager(DetailsActivity.this, 2);
                    listRecyclerView.setLayoutManager(mLayoutManager);
                    listRecyclerView.setHasFixedSize(true);
                    adapter.notifyDataSetChanged();
                } else {
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(DetailsActivity.this);
                    listRecyclerView.setLayoutManager(mLayoutManager);
                    listRecyclerView.setHasFixedSize(true);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(DetailsActivity.this);
        listRecyclerView.setLayoutManager(mLayoutManager);
        listRecyclerView.setHasFixedSize(true);


        getdata();
    }

    public void getdata() {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("pscid", pscid);
        params.addBodyParameter("page", page);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    final ListBean listBean = gson.fromJson(result, ListBean.class);
                    adapter = new ListAdapter(listBean.getData(), DetailsActivity.this);
                    listRecyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(DetailsActivity.this, XqActivity.class);
                            intent.putExtra("pid", listBean.getData().get(position).getPid() + "");
                            startActivity(intent);
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

    public void initView() {
        listRecyclerView = (RecyclerView) findViewById(R.id.list_RecyclerView);
        horver = (RadioButton) findViewById(R.id.horver);
        soso = (EditText) findViewById(R.id.soso);
        soso.setOnClickListener(this);
        list_RecyclerView = (RecyclerView) findViewById(R.id.list_RecyclerView);
        list_RecyclerView.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String sosoString = soso.getText().toString().trim();
        if (TextUtils.isEmpty(sosoString)) {
            Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.soso:


                        Intent intent=new Intent(DetailsActivity.this,SousuoActivity.class);
                        startActivity(intent);

                break;
        }
    }
}
