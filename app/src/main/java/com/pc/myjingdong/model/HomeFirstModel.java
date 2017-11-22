package com.pc.myjingdong.model;

import android.util.Log;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.HomeFirstBean;
import com.pc.myjingdong.utils.API;
import com.pc.myjingdong.utils.ApiService;
import com.pc.myjingdong.utils.OnNetListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc on 2017/11/3.
 */

public class HomeFirstModel {
    public void getFirstData(final OnNetListener onNetListener){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URI)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<HomeFirstBean> call = apiService.firsthome();
        call.enqueue(new Callback<HomeFirstBean>() {
            @Override
            public void onResponse(Call<HomeFirstBean> call, Response<HomeFirstBean> response) {
                BaseBean baseBean = response.body();
                String msg = baseBean.getMsg();
                Log.e("TAG-----",msg);
                onNetListener.onSuccess(baseBean);
            }

            @Override
            public void onFailure(Call<HomeFirstBean> call, Throwable t) {

            }
        });
    }
}
