package com.pc.myjingdong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.GoodsInfoBean;
import com.pc.myjingdong.presenter.GoodsInfoPres;
import com.pc.myjingdong.view.GoodsInfoView;

/**
 * Created by pc on 2017/11/8.
 */

public class FragmentInfo2 extends Fragment {
    private View view;
    private WebView mInfo2Web;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_info2, container, false);
        initView(view);

        Intent intent = getActivity().getIntent();
        String pid = intent.getStringExtra("pid");

        GoodsInfoPres goodsInfoPres = new GoodsInfoPres(new GoodsInfoView() {
            @Override
            public void infoShow(GoodsInfoBean goodsInfoBean) {
                String detailUrl = goodsInfoBean.getData().getDetailUrl();
                //设置webview
                mInfo2Web.loadUrl(detailUrl);
            }
        });
        goodsInfoPres.infoMandV(pid);

        return view;
    }

    private void initView(View view) {
        mInfo2Web = (WebView) view.findViewById(R.id.info2_web);
    }
}
