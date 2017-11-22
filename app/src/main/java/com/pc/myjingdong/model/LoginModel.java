package com.pc.myjingdong.model;

import android.util.Log;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.LoginBean;
import com.pc.myjingdong.utils.API;
import com.pc.myjingdong.utils.ApiService;
import com.pc.myjingdong.utils.OnNetListener;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc on 2017/11/2.
 */

public class LoginModel {

    public void getLoginData(String user, String pwd, final OnNetListener onNetListener){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //请求数据
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(API.URI)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<LoginBean> call = apiService.login(user, pwd);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                BaseBean baseBean = response.body();
                String msg = baseBean.getMsg();
                Log.e("TAG----",msg);
                onNetListener.onSuccess(baseBean);
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });
    }


}
