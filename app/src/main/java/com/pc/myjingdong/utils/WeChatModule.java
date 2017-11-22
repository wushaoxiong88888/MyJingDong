package com.pc.myjingdong.utils;

import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by pc on 2017/11/17.
 */

public class WeChatModule {

    private static WeChatModule weChatModule;
    private static final String APP_ID = "12312313212313213213";    //这个APP_ID就是注册APP的时候生成的

    private static final String APP_SECRET = "12312312313212313213213";

    public IWXAPI api;      //这个对象是专门用来向微信发送数据的一个重要接口,使用强引用持有,所有的信息发送都是基于这个对象的

    public void registerWeChat(Context context) {   //向微信注册app
        api = WXAPIFactory.createWXAPI(context, APP_ID, true);
        api.registerApp(APP_ID);
    }

    public static WeChatModule getInstance(){

        if(weChatModule==null){
            synchronized (WeChatModule.class){
                if(weChatModule==null){
                    weChatModule = new WeChatModule();
                }
            }
        }
        return weChatModule;

    }

}
