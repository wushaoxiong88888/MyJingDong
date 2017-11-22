package com.pc.myjingdong.model;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.GoodsInfoBean;
import com.pc.myjingdong.utils.API;
import com.pc.myjingdong.utils.ApiService;
import com.pc.myjingdong.utils.OnNetListener;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2017/11/8.
 */

public class GoodsInfoModel {
    public void infoGetData(String pid, final OnNetListener onNetListener){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.URI)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<GoodsInfoBean> observable = apiService.goodsinfo(pid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GoodsInfoBean, BaseBean>() {
                    @Override
                    public BaseBean call(GoodsInfoBean goodsInfoBean) {
                        return goodsInfoBean;
                    }
                })
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        String code = baseBean.getCode();
                        //Log.e("TAG----",code);
                        onNetListener.onSuccess(baseBean);
                    }
                });
    }
}
