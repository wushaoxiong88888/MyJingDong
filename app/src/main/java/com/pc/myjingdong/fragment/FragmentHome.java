package com.pc.myjingdong.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.pc.myjingdong.R;
import com.pc.myjingdong.adapter.FirstGridRlvAdapter;
import com.pc.myjingdong.adapter.FirstRlvAdapter;
import com.pc.myjingdong.bean.HomeFirstBean;
import com.pc.myjingdong.presenter.HomeFirstPres;
import com.pc.myjingdong.utils.GlideImageLoader;
import com.pc.myjingdong.view.HomeFirstView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/10/31.
 */

public class FragmentHome extends Fragment implements View.OnClickListener {
    private View view;
    private Banner mFragHomeBan;
    private List<String> list = new ArrayList<>();
    private ImageView mFragHomeIv;
    private TextView mFragHomeTvMiao;
    private RecyclerView mFragHomeRlv;
    private RecyclerView mFragHomeRlv2;
    /**
     * 10
     */
    private Button mFragHomeBtTime;
    private int count = 60;
    private LinearLayout mFragHomeSao;
    private static final int REQ_CODE_PERMISSION = 0x1111;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_home, container, false);

        //初始化资源
        initView(view);

        //显示数据
        homefirstData();

        //设置定时器
        handler.postDelayed(runnable, 1000);

        return view;
    }

    //设置定时器
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000);
            mFragHomeBtTime.setText(Integer.toString(count--));
            if (count == 0) {
                count = 60;
            }
        }
    };

    private void homefirstData() {
        HomeFirstPres homeFirstPres = new HomeFirstPres(new HomeFirstView() {
            @Override
            public void showFirstData(HomeFirstBean homeFirstBean) {
                List<HomeFirstBean.DataBean> data = homeFirstBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    String icon = data.get(i).getIcon();
                    String title = data.get(i).getTitle();
                    //Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();
                    list.add(icon);
                }
                //轮播图Banner设置
                mFragHomeBan.setImageLoader(new GlideImageLoader());
                mFragHomeBan.setImages(list);
                mFragHomeBan.setDelayTime(2000);
                mFragHomeBan.start();

                //得到值并设置上去
                String name = homeFirstBean.getMiaosha().getName();
                mFragHomeTvMiao.setText(name);

                //左右滑动的rlv
                List<HomeFirstBean.MiaoshaBean.ListBeanX> list = homeFirstBean.getMiaosha().getList();
                //设置秒杀的横向rlv
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mFragHomeRlv.setLayoutManager(linearLayoutManager);
                //设置设配器
                FirstRlvAdapter adapter = new FirstRlvAdapter(getActivity(), list);
                mFragHomeRlv.setAdapter(adapter);

                //设置推荐的网格rlv
                List<HomeFirstBean.TuijianBean.ListBean> list1 = homeFirstBean.getTuijian().getList();
                mFragHomeRlv2.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                //设置设配器
                mFragHomeRlv2.setAdapter(new FirstGridRlvAdapter(getActivity(), list1));

            }
        });
        homeFirstPres.homeFistMandV();
    }

    private void initView(View view) {
        mFragHomeBan = (Banner) view.findViewById(R.id.frag_home_ban);
        mFragHomeIv = (ImageView) view.findViewById(R.id.frag_home_iv);
        mFragHomeTvMiao = (TextView) view.findViewById(R.id.frag_home_tv_miao);
        mFragHomeRlv = (RecyclerView) view.findViewById(R.id.frag_home_rlv);
        mFragHomeRlv2 = (RecyclerView) view.findViewById(R.id.frag_home_rlv2);
        mFragHomeBtTime = (Button) view.findViewById(R.id.frag_home_bt_time);
        mFragHomeSao = (LinearLayout) view.findViewById(R.id.frag_home_sao);
        mFragHomeSao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_home_sao:

                // Open Scan Activity
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Do not have the permission of camera, request it.
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
                } else {
                    // Have gotten the permission
                    startCaptureActivityForResult();
                }


                break;
        }
    }

    private void startCaptureActivityForResult() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        bundle.putBoolean(CaptureActivity.KEY_SCAN_AREA_FULL_SCREEN, CaptureActivity.VALUE_SCAN_AREA_FULL_SCREEN);
        bundle.putBoolean(CaptureActivity.KEY_NEED_SCAN_HINT_TEXT, CaptureActivity.VALUE_SCAN_HINT_TEXT);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }


}
