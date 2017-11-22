package com.pc.myjingdong.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.pc.myjingdong.bean.BaseBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * okhttp的二次封装
 * Created by pc on 2017/11/9.
 */

public class OkHttpUtils {

    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler(Looper.getMainLooper());

    public OkHttpUtils(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                //添加拦截器
                .addInterceptor(interceptor)
                .build();
    }

    //单例模式
    //懒汉式,二次检索创建
    public static final OkHttpUtils getInstance(){
        if(okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if(okHttpUtils==null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    //一个简单的get请求
    public void doGet(String url, final Class clazz, final OnNetListener onNetListener){
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final BaseBean baseBean = (BaseBean) new Gson().fromJson(string,clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(baseBean);
                    }
                });
            }
        });
    }

}
