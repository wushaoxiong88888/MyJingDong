package com.pc.myjingdong.model;

import android.util.Log;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.GetUserInfoBean;
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

public class GetInfoModel {
    public void getuserData(String uid,String token, final OnNetListener onNetListener){
        //获取数据
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URI)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<GetUserInfoBean> call = apiService.getinfo(uid,token);
        call.enqueue(new Callback<GetUserInfoBean>() {
            @Override
            public void onResponse(Call<GetUserInfoBean> call, Response<GetUserInfoBean> response) {
                BaseBean baseBean = response.body();
                String msg = baseBean.getMsg();
                Log.e("TAG-----",msg);
                onNetListener.onSuccess(baseBean);
            }

            @Override
            public void onFailure(Call<GetUserInfoBean> call, Throwable t) {

            }
        });
    }
}
