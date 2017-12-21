package com.example.lenovo.jd.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.FirstEvent;
import com.example.lenovo.jd.bean.UserBean;
import com.example.lenovo.jd.fragment.Fragmentshopincar;
import com.example.lenovo.jd.presenter.UserBeanPresenterImpl;
import com.example.lenovo.jd.view.UserBeanView;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends AppCompatActivity implements UserBeanView {

    private EditText loginname;
    private EditText loginpwd;
    private TextView zuce;
    private Button login;
    private UserBeanPresenterImpl presenter;
    private String url= API.DENGLU;
    private int uid;
    private String nickname;
    private String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginname= (EditText) findViewById(R.id.loginname);
        loginpwd= (EditText) findViewById(R.id.loginpwd);
        zuce= (TextView) findViewById(R.id.zuce);
        login= (Button) findViewById(R.id.login);

        zuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(LoginActivity.this,ZuceActivity.class);
                startActivity(inten);
            }
        });
        presenter=new UserBeanPresenterImpl(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = loginname.getText().toString();
                String s1 = loginpwd.getText().toString();
                //p关联m 做网络请求
                presenter.relevance(url,s,s1);
            }
        });
    }
    @Override
    public void showData(final UserBean userBean,String str) {

        if (userBean.getMsg().equals("登录成功")){
            uid = userBean.getData().getUid();
            String icon = userBean.getData().getIcon();
            String nickname = userBean.getData().getNickname();
            getSharedPreferences("logUser",MODE_PRIVATE).edit().putString("uid",uid+"").commit();
            getSharedPreferences("logUser",MODE_PRIVATE).edit().putString("icon",icon).putString("nickname",nickname).commit();
            getSharedPreferences("logUser",MODE_PRIVATE).edit().putBoolean("yes",true).commit();
            EventBus.getDefault().post(
                    new FirstEvent("0"));
            finish();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, ""+userBean.getMsg(), Toast.LENGTH_SHORT).show();

            }
        });




    }

}
