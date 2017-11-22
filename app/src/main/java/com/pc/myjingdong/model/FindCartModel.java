package com.pc.myjingdong.model;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.FindCartBean;
import com.pc.myjingdong.utils.API;
import com.pc.myjingdong.utils.ApiService;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.utils.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2017/11/9.
 */

public class FindCartModel {
    public void cartGetData(String uid, String token, final OnNetListener onNetListener){
        /*Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.URI)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);*/

        ApiService apiService = RetrofitUtils.getInstance().getApiService(API.URI, ApiService.class);

        Observable<FindCartBean> observable = apiService.findcart(uid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<FindCartBean, BaseBean>() {
                    @Override
                    public BaseBean call(FindCartBean findCartBean) {
                        return findCartBean;
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
                        onNetListener.onSuccess(baseBean);
                    }
                });

    }
}
