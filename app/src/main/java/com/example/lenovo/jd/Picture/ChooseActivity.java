package com.example.lenovo.jd.Picture;

import java.io.File;
import java.util.LinkedList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.lenovo.jd.Picture.adapter.UploadImageAdapter;
import com.example.lenovo.jd.Picture.utils.ImageUtils;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.inter.ApiService;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChooseActivity extends BaseActivity {

	/**
	 * 需要上传的图片路径 控制默认图片在最后面需要用LinkedList
	 */
	private LinkedList<String> dataList = new LinkedList<String>();
	/**
	 * 图片上传GridView
	 */
	private GridView uploadGridView;
	/**
	 * 图片上传Adapter
	 */
	private UploadImageAdapter adapter;
	private ImageView lm;
	private View qr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose);
//		findViewById(R.id.btn_change_fragment).setOnClickListener(
//				new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						startActivity(new Intent(ChooseActivity.this,
//								ChooseFragmentActivity.class));
//					}
//				});
		uploadGridView = (GridView) findViewById(R.id.grid_upload_pictures);
		dataList.addLast(null);// 初始化第一个添加按钮数据
		adapter = new UploadImageAdapter(this, dataList);
		uploadGridView.setAdapter(adapter);
		uploadGridView.setOnItemClickListener(mItemClick);
		uploadGridView.setOnItemLongClickListener(mItemLongClick);
		qr = findViewById(R.id.qr);
		qr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(getBaseContext(),MainActivity.class);
//				intent.putExtra("aaa",dataList.get(0));
				if (dataList.get(0)!=null){
					uploadPic2(dataList.get(0).toString());
					Toast.makeText(ChooseActivity.this, "上传成功"+dataList.get(0).toString(), Toast.LENGTH_SHORT).show();
//				startActivity(intent);

					finish();
				}else {
					Toast.makeText(ChooseActivity.this, "请添加图片", Toast.LENGTH_SHORT).show();

				}


			}

			//上传图片 参数有2个 uid  file
			private void uploadPic2(String s) {
				//创建Retrofit
				Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.27.23.105/").addConverterFactory(GsonConverterFactory.create()).build();
				////通过动态代理的方式得到网络接口对象
				ApiService apiService = retrofit.create(ApiService.class);
				//图片文件
				File file = new File(s);

				//创建RequestBody
				RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
				MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
				String uids =getSharedPreferences("logUser", Context.MODE_PRIVATE).getString("uid", null);;//动态获取
				RequestBody uid = RequestBody.create(MediaType.parse("multipart/form-data"), uids);
				Call<User> call = apiService.uploadPic2(uid, body);
				call.enqueue(new Callback<User>() {
					@Override
					public void onResponse(Call<User> call, Response<User> response) {
						getSharedPreferences("logUser",MODE_PRIVATE).edit().putString("icon","https://www.zhaoapi.cn/images/72.jpg").commit();
						Toast.makeText(getBaseContext(), "上传成功"+response.body().toString(), Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(Call<User> call, Throwable t) {

					}
				});


			}
		});
	}

	/**
	 * 上传图片GridView Item单击监听
	 */
	private OnItemClickListener mItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (parent.getItemAtPosition(position) == null) { // 添加图片
				// showPictureDailog();//Dialog形式
				showPicturePopupWindow();// PopupWindow形式
			}
		}
	};

	/**
	 * 上传图片GridView Item长按监听
	 */
	private OnItemLongClickListener mItemLongClick = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			if (parent.getItemAtPosition(position) != null) { // 长按删除
				dataList.remove(parent.getItemAtPosition(position));
				adapter.update(dataList); // 刷新图片
			}
			return true;
		}
	};

	String[] proj = { MediaColumns.DATA };

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SELECT_IMAGE_RESULT_CODE && resultCode == RESULT_OK) {
			String imagePath = "";
			Uri uri = null;
			if (data != null && data.getData() != null) {// 有数据返回直接使用返回的图片地址
				uri = data.getData();
				Cursor cursor = getContentResolver().query(uri, proj, null,
						null, null);
				if (cursor == null) {
					uri = ImageUtils.getUri(this, data);
				}
				imagePath = ImageUtils.getFilePathByFileUri(this, uri);
			} else {// 无数据使用指定的图片路径
				imagePath = mImagePath;
			}
			dataList.addFirst(imagePath);
			adapter.update(dataList); // 刷新图片
		}
	}
}
