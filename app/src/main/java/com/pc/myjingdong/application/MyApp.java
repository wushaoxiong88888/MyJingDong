package com.pc.myjingdong.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by pc on 2017/11/3.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        //友盟分享
        Config.DEBUG = true;
        UMShareAPI.get(this);
    }

    {
        PlatformConfig.setWeixin("","");
        PlatformConfig.setQQZone("1106467562","RJ5VKiYujOVI08k7");
        PlatformConfig.setSinaWeibo("","","");
    }


}
