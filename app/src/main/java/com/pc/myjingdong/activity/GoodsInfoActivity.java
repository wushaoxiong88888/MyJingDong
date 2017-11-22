package com.pc.myjingdong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.GoodsInfoBean;
import com.pc.myjingdong.fragment.FragmentInfo1;
import com.pc.myjingdong.fragment.FragmentInfo2;
import com.pc.myjingdong.fragment.FragmentInfo3;
import com.pc.myjingdong.presenter.AddCartPres;
import com.pc.myjingdong.presenter.GoodsInfoPres;
import com.pc.myjingdong.view.AddCartView;
import com.pc.myjingdong.view.GoodsInfoView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class GoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mInfoIvBack;
    private PagerSlidingTabStrip mInfoPsts;
    private ViewPager mInfoVp;
    private LinearLayout mInfoLlBottom1;
    private LinearLayout mInfoLlBottom2;
    private LinearLayout mInfoLlBottom3;
    private LinearLayout mInfoLlBottom4;
    private LinearLayout mInfoLlBottom5;
    private String pid;
    private SharedPreferences preferences;
    private ImageView mInfoShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        initView();

        //得到传过来的pid
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        //Toast.makeText(GoodsInfoActivity.this,pid,Toast.LENGTH_SHORT).show();

        //得到SharedPreferences
        preferences = getSharedPreferences("user", MODE_PRIVATE);

        mInfoVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] titles = {"商家", "详情", "评论"};

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public Fragment getItem(int position) {

                Fragment fragment = null;

                switch (position) {
                    case 0:
                        fragment = new FragmentInfo1();
                        break;
                    case 1:
                        fragment = new FragmentInfo2();
                        break;
                    case 2:
                        fragment = new FragmentInfo3();
                        break;
                }

                return fragment;
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
        mInfoPsts.setViewPager(mInfoVp);

    }

    private void initView() {
        mInfoIvBack = (ImageView) findViewById(R.id.info_iv_back);
        mInfoIvBack.setOnClickListener(this);
        mInfoPsts = (PagerSlidingTabStrip) findViewById(R.id.info_psts);
        mInfoVp = (ViewPager) findViewById(R.id.info_vp);
        mInfoLlBottom1 = (LinearLayout) findViewById(R.id.info_llBottom1);
        mInfoLlBottom1.setOnClickListener(this);
        mInfoLlBottom2 = (LinearLayout) findViewById(R.id.info_llBottom2);
        mInfoLlBottom2.setOnClickListener(this);
        mInfoLlBottom3 = (LinearLayout) findViewById(R.id.info_llBottom3);
        mInfoLlBottom3.setOnClickListener(this);
        mInfoLlBottom4 = (LinearLayout) findViewById(R.id.info_llBottom4);
        mInfoLlBottom4.setOnClickListener(this);
        mInfoLlBottom5 = (LinearLayout) findViewById(R.id.info_llBottom5);
        mInfoLlBottom5.setOnClickListener(this);
        mInfoShare = (ImageView) findViewById(R.id.info_share);
        mInfoShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_iv_back:
                finish();
                break;
            case R.id.info_llBottom1:
                break;
            case R.id.info_llBottom2:
                break;
            case R.id.info_llBottom3:
                break;
            case R.id.info_llBottom4:

                Intent intent = new Intent(GoodsInfoActivity.this, CartActivity.class);
                startActivity(intent);

                break;
            case R.id.info_llBottom5:

                String uid = preferences.getString("uid", "");
                String token = preferences.getString("token", "");

                //加入购物车
                AddCartPres addCartPres = new AddCartPres(new AddCartView() {
                    @Override
                    public void addcartShow(BaseBean baseBean) {
                        String msg = baseBean.getMsg();
                        Toast.makeText(GoodsInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                addCartPres.cartMandV(uid, pid, token);
                break;
            case R.id.info_share:

                ShareWeb(R.mipmap.gou);

                break;
        }
    }

    //友盟第三方分享
    private void ShareWeb(final int thumb_img) {

        GoodsInfoPres goodsInfoPres = new GoodsInfoPres(new GoodsInfoView() {
            @Override
            public void infoShow(GoodsInfoBean goodsInfoBean) {
                String detailUrl = goodsInfoBean.getData().getDetailUrl();

                UMImage thumb = new UMImage(GoodsInfoActivity.this, thumb_img);
                UMWeb web = new UMWeb(detailUrl);
                web.setThumb(thumb);
                web.setDescription("让你的努力配得上你的梦想");
                web.setTitle("京东详情页");
                new ShareAction(GoodsInfoActivity.this).withMedia(web).setPlatform(SHARE_MEDIA.QQ).setCallback(shareListener).share();
            }
        });
        goodsInfoPres.infoMandV(pid);


    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(GoodsInfoActivity.this, "分享开始了", Toast.LENGTH_SHORT).show();
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(GoodsInfoActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(GoodsInfoActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(GoodsInfoActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

}
