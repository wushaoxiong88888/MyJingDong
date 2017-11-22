package com.pc.myjingdong.model;

import android.util.Log;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.GoodsListBean;
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

public class GoodsListModel {

    //private Handler handler = new Handler();

    public void listGetData(String pscid, int page, int sort, final OnNetListener onNetListener) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URI)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<GoodsListBean> call = apiService.goodslist(pscid,page,sort);
        call.enqueue(new Callback<GoodsListBean>() {
            @Override
            public void onResponse(Call<GoodsListBean> call, Response<GoodsListBean> response) {
                BaseBean baseBean = response.body();
                String msg = baseBean.getMsg();
                Log.e("TAG-----", msg);
                onNetListener.onSuccess(baseBean);
            }

            @Override
            public void onFailure(Call<GoodsListBean> call, Throwable t) {

            }
        });
    }

}
