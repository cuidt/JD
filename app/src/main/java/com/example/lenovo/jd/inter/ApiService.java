package com.example.lenovo.jd.inter;


import com.example.lenovo.jd.Picture.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;



public interface ApiService {

    @Multipart
    @POST("file/upload")
    Call<User> uploadPic2(@Part("uid") RequestBody uid, @Part MultipartBody.Part file);

    @Multipart
    @POST("user/updateNickName")
    Call<User> updateNickName(@Part("uid") RequestBody uid, @Part("nickname") RequestBody nickname);
}
