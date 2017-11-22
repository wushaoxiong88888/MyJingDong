package com.pc.myjingdong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.GoodsInfoBean;
import com.pc.myjingdong.presenter.GoodsInfoPres;
import com.pc.myjingdong.utils.GlideImageLoader;
import com.pc.myjingdong.view.GoodsInfoView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/11/8.
 */

public class FragmentInfo1 extends Fragment {
    private View view;
    private Banner mInfo1Banner;
    /**
     * 标题
     */
    private TextView mInfo1Title;
    /**
     * 描述
     */
    private TextView mInfo1Subhead;
    /**
     * 价格
     */
    private TextView mInfo1Price;
    private List<String> imglist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_info1, container, false);

        Intent intent = getActivity().getIntent();
        String pid = intent.getStringExtra("pid");

        initView(view);

        GoodsInfoPres goodsInfoPres = new GoodsInfoPres(new GoodsInfoView() {
            @Override
            public void infoShow(GoodsInfoBean goodsInfoBean) {
                GoodsInfoBean.DataBean list = goodsInfoBean.getData();
//                String title = list.getTitle();
//                Toast.makeText(getActivity(),title,Toast.LENGTH_SHORT).show();
                String images = list.getImages();
                String[] imgs = images.split("\\|");
                for (int i = 0; i < imgs.length; i++) {
                    String s = imgs[0].toString();
                    imglist.add(s);
                }
                //设置值
                //banner加载器
                mInfo1Banner.setImageLoader(new GlideImageLoader());
                mInfo1Banner.setImages(imglist);
                mInfo1Banner.setDelayTime(2000);
                mInfo1Banner.start();

                mInfo1Title.setText(list.getTitle());
                mInfo1Subhead.setText(list.getSubhead());
                mInfo1Price.setText("人民币:"+String.valueOf(list.getPrice()));
            }
        });
        goodsInfoPres.infoMandV(pid);

        return view;
    }

    private void initView(View view) {
        mInfo1Banner = (Banner) view.findViewById(R.id.info1_banner);
        mInfo1Title = (TextView) view.findViewById(R.id.info1_title);
        mInfo1Subhead = (TextView) view.findViewById(R.id.info1_subhead);
        mInfo1Price = (TextView) view.findViewById(R.id.info1_price);
    }
}
