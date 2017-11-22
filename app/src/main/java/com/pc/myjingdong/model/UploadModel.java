package com.pc.myjingdong.model;

import android.util.Log;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.LoginBean;
import com.pc.myjingdong.utils.API;
import com.pc.myjingdong.utils.ApiService;
import com.pc.myjingdong.utils.OnNetListener;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc on 2017/11/2.
 */

public class UploadModel {

    public void getUploadData(String uid,String token, final OnNetListener onNetListener){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URI)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        //图片文件
        File file = new File("/mnt/shared/Image/ic_default_user_head.gif");
        //创建RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file",file.getName(),requestBody);

        RequestBody uid1 = RequestBody.create(MediaType.parse("multipart/form-data"),uid);
        Call<LoginBean> call = apiService.upload(uid1, body,token);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                BaseBean baseBean = response.body();
                String msg = baseBean.getMsg();
                Log.e("TAG-----",msg);
                onNetListener.onSuccess(baseBean);
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });

    }

}
