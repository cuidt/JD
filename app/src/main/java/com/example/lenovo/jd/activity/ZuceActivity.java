package com.example.lenovo.jd.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.UserBean;
import com.example.lenovo.jd.presenter.UserBeanPresenterImpl;
import com.example.lenovo.jd.view.UserBeanView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ZuceActivity extends AppCompatActivity implements UserBeanView{
    private EditText name;
    private EditText pwd;
    private Button zuce;
    private String url= API.ZUCE;
    private UserBeanPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuce);
        name= (EditText) findViewById(R.id.zuce_name);
        pwd= (EditText) findViewById(R.id.zuce_pwd);
        zuce= (Button) findViewById(R.id.zuce_zuce);
        presenter =new UserBeanPresenterImpl(this);
        zuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ZuceActivity.this, "注册", Toast.LENGTH_SHORT).show();
                presenter.relevance(url,name.getText().toString(),pwd.getText().toString());

    }
});

    }

    @Override
    public void showData(final UserBean userBean, final String str) {
        if (str.equals("注册成功")){
            finish();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ZuceActivity.this ,str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
