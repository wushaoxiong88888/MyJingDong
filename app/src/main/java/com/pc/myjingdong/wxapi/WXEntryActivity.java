package com.pc.myjingdong.wxapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pc.myjingdong.R;
import com.pc.myjingdong.utils.WeChatModule;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        WeChatModule.getInstance().api.handleIntent(getIntent(), this); //处理微信传回的Intent,当然你也可以在别的地方处理
    }

    @Override
    public void onResp(BaseResp resp) { //在这个方法中处理微信传回的数据
        //形参resp 有下面两个个属性比较重要
        //1.resp.errCode
        //2.resp.transaction则是在分享数据的时候手动指定的字符创,用来分辨是那次分享(参照4.中req.transaction)
        switch (resp.errCode) { //根据需要的情况进行处理
            case BaseResp.ErrCode.ERR_OK:
                //正确返回
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //认证被否决
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                //发送失败
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                //不支持错误
                break;
            case BaseResp.ErrCode.ERR_COMM:
                //一般错误
                break;
            default:
                //其他不可名状的情况
                break;
        }
    }

    @Override
    public void onReq(BaseReq req) {
        //......这里是用来处理接收的请求,暂不做讨论
    }

}
