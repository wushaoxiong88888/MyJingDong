package com.pc.myjingdong.model;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.ClassRightBean;
import com.pc.myjingdong.utils.API;
import com.pc.myjingdong.utils.ApiService;
import com.pc.myjingdong.utils.OnNetListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc on 2017/11/7.
 */

public class ClassRightModel {
    public void classRightGetData(int cid, final OnNetListener onNetListener){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URI)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ClassRightBean> call = apiService.classright(cid);
        call.enqueue(new Callback<ClassRightBean>() {
            @Override
            public void onResponse(Call<ClassRightBean> call, Response<ClassRightBean> response) {
                BaseBean baseBean = response.body();
                String msg = baseBean.getMsg();
                //Log.e("TAG----",msg);
                onNetListener.onSuccess(baseBean);
            }

            @Override
            public void onFailure(Call<ClassRightBean> call, Throwable t) {

            }
        });
    }
}
