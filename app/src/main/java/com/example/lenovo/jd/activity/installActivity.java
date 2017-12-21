package com.example.lenovo.jd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.jd.Picture.ChooseActivity;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.utils.XCRoundImageView;
import com.squareup.picasso.Picasso;

public class installActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tuichu;
    private XCRoundImageView image_icon;
    private TextView te_name;
    private String icon;
    private String nickname;
    private ImageView back;
    private View lognichen;
    private View image_ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);
        icon = getSharedPreferences("logUser", Context.MODE_PRIVATE).getString("icon", null);
        nickname = getSharedPreferences("logUser", Context.MODE_PRIVATE).getString("nickname", null);
        initView();
    }
    private void initView() {
        tuichu = (Button) findViewById(R.id.tuichu);
        image_icon = findViewById(R.id.image_icon);

        image_ic = findViewById(R.id.image_ic);
        Picasso.with(this).load(icon).into(image_icon);
        te_name = findViewById(R.id.te_name);
        te_name.setText(nickname);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        tuichu.setOnClickListener(this);
        image_icon.setOnClickListener(this);
        lognichen = findViewById(R.id.lognichen);
        lognichen.setOnClickListener(this);
        image_ic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tuichu:
                getSharedPreferences("logUser", Context.MODE_PRIVATE).edit().clear().commit();
                finish();
                break;
            case R.id.image_ic:
                startActivity(new Intent(installActivity.this, ChooseActivity.class));
                break;
            case R.id.back:
               finish();
                break;

            case R.id.lognichen:
                startActivity(new Intent(installActivity.this, updateNickName.class));
                break;
        }
    }
}
