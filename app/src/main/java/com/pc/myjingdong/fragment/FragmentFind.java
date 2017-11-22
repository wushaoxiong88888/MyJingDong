package com.pc.myjingdong.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.utils.WeChatModule;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

import java.io.File;

/**
 * Created by pc on 2017/10/31.
 */

public class FragmentFind extends Fragment implements View.OnClickListener {
    private View view;
    /**
     * 微信分享
     */
    private TextView mTvWeChatShare;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_find, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvWeChatShare = (TextView) view.findViewById(R.id.tv_WeChatShare);
        mTvWeChatShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_WeChatShare:
                break;
        }
    }

    //调用这个方法之后会自动打开微信的界面,可以发送你要分享的信息了!
    public void sharePicByFile(File picFile, String tag) {
        if (!picFile.exists()) {return;}
        Bitmap pic = BitmapFactory.decodeFile(picFile.toString());

        WXImageObject imageObject = new WXImageObject(pic);
        //这个构造方法中自动把传入的bitmap转化为2进制数据,或者你直接传入byte[]也行
        //注意传入的数据不能大于10M,开发文档上写的

        WXMediaMessage msg = new WXMediaMessage();  //这个对象是用来包裹发送信息的对象
        msg.mediaObject = imageObject;
        //msg.mediaObject实际上是个IMediaObject对象,
        //它有很多实现类,每一种实现类对应一种发送的信息,
        //比如WXTextObject对应发送的信息是文字,想要发送文字直接传入WXTextObject对象就行



        Bitmap thumbBitmap = Bitmap.createScaledBitmap(pic, 150, 150, true);

        msg.setThumbImage(thumbBitmap);
        //在这设置缩略图
        //官方文档介绍这个bitmap不能超过32kb
        //如果一个像素是8bit的话换算成正方形的bitmap则边长不超过181像素,边长设置成150是比较保险的
        //或者使用msg.setThumbImage(thumbBitmap);省去自己转换二进制数据的过程
        //如果超过32kb则抛异常

        SendMessageToWX.Req req = new SendMessageToWX.Req();    //创建一个请求对象
        req.message = msg;  //把msg放入请求对象中
        req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
        //req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
        req.transaction = tag;  //这个tag要唯一,用于在回调中分辨是哪个分享请求
        boolean b = WeChatModule.getInstance().api.sendReq(req);   //如果调用成功微信,会返回true
    }

}
