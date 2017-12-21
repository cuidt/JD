package com.example.lenovo.jd.activity;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.api.API;
import com.example.lenovo.jd.bean.Gjcbean;
import com.example.lenovo.jd.utils.ToastUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class SousuoActivity extends AppCompatActivity {
    private EditText mEditText;
    private ImageView mImageView;
    private ListView mListView;
    private TextView mTextView;
    Context context;
    Cursor cursor;
    private String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        context = this;
        initView();
    }
    private void initView() {
        mTextView = (TextView) findViewById(R.id.textview);
        mEditText = (EditText) findViewById(R.id.edittext);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mListView = (ListView) findViewById(R.id.listview);
        //设置删除图片的点击事件
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把EditText内容设置为空
                mEditText.setText("");
                //把ListView隐藏
                mListView.setVisibility(View.GONE);
            }
        });
        //EditText添加监听
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            //文本改变之前执行
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果长度为0
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    mImageView.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    mImageView.setVisibility(View.VISIBLE);
                    //显示ListView
                    showListView();
                }
            }
            //文本改变之后执行
            public void afterTextChanged(Editable s) {
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果输入框内容为空，提示请输入搜索内容
                if(TextUtils.isEmpty(mEditText.getText().toString().trim())){
                    ToastUtils.showToast(context,"请输入您要搜索的内容");
                }else {
                    RequestParams params=new RequestParams(API.GUANJIANCI);
                    params.addBodyParameter("keywords", mEditText.getText().toString());
                    params.addBodyParameter("source", "android");
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson=new Gson();
                            Gjcbean gjcbean=gson.fromJson(result, Gjcbean.class);
                            String name1=gjcbean.getData().get(0).getPscid()+"";
                            ToastUtils.showShortToast(context,mEditText.getText().toString().trim()+"有结果");
                                Intent intent=new Intent(SousuoActivity.this,DetailsActivity.class);
                                intent.putExtra("gscid",name1);
                                startActivity(intent);
                        }
                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Intent intent=new Intent(SousuoActivity.this,DetailsActivity.class);
                            intent.putExtra("gscid","40");
                            startActivity(intent);
                            ToastUtils.showToast(context, "对不起，没有你要搜索的内容");
                        }
                        @Override
                        public void onCancelled(CancelledException cex) {
                        }
                        @Override
                        public void onFinished() {
                        }
                    });
                }

            }
        });
    }

    private void showListView() {
        mListView.setVisibility(View.VISIBLE);
        //获得输入的内容
        str = mEditText.getText().toString().trim();
//        //获取数据库对象
//        MyOpenHelper myOpenHelper = new MyOpenHelper(getApplicationContext());
//        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
//        //得到cursor
//       // cursor = db.rawQuery("select * from lol where name like '%" + str + "%'", null);
//        MyListViewCursorAdapter adapter = new MyListViewCursorAdapter(context, cursor);
//        mListView.setAdapter(adapter);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ToastUtils.showShortToast(context,"有结果");
//                //把cursor移动到指定行
//                cursor.moveToPosition(position);
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//                Intent intent=new Intent(SousuoActivity.this,DetailsActivity.class);
//                intent.putExtra("gscid",name);
//                startActivity(intent);
//            }
//        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //把cursor移动到指定行
               // cursor.moveToPosition(position);
               // String name = cursor.getString(cursor.getColumnIndex("name"));
//                Intent intent=new Intent(SousuoActivity.this,DetailsActivity.class);
//                intent.putExtra("gscid",name);
//                startActivity(intent);
            }
        });

    }
}
