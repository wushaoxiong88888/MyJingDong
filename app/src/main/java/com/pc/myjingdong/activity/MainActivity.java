package com.pc.myjingdong.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.fragment.FragmentCart;
import com.pc.myjingdong.fragment.FragmentClass;
import com.pc.myjingdong.fragment.FragmentFind;
import com.pc.myjingdong.fragment.FragmentHome;
import com.pc.myjingdong.fragment.FragmentMy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mMainFl;
    private ImageView mMainIvHome;
    private ImageView mMainIvClass;
    private ImageView mMainIvFind;
    private ImageView mMainIvCart;
    private ImageView mMainIvMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化组件
        initView();

        //得到fragment管理器
        //默认显示首页fragment
        getFragMsg();
    }

    private void getFragMsg() {
        mMainIvHome.setImageResource(R.mipmap.ac1);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fl, new FragmentHome());
        transaction.commit();
    }

    private void initView() {
        mMainFl = (FrameLayout) findViewById(R.id.main_fl);
        mMainIvHome = (ImageView) findViewById(R.id.main_iv_home);
        mMainIvHome.setOnClickListener(this);
        mMainIvClass = (ImageView) findViewById(R.id.main_iv_class);
        mMainIvClass.setOnClickListener(this);
        mMainIvFind = (ImageView) findViewById(R.id.main_iv_find);
        mMainIvFind.setOnClickListener(this);
        mMainIvCart = (ImageView) findViewById(R.id.main_iv_cart);
        mMainIvCart.setOnClickListener(this);
        mMainIvMy = (ImageView) findViewById(R.id.main_iv_my);
        mMainIvMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //点击全部选择默认图片
        mMainIvHome.setImageResource(R.mipmap.ac0);
        mMainIvClass.setImageResource(R.mipmap.abw);
        mMainIvFind.setImageResource(R.mipmap.aby);
        mMainIvCart.setImageResource(R.mipmap.abu);
        mMainIvMy.setImageResource(R.mipmap.ac2);

        switch (v.getId()) {
            case R.id.main_iv_home:

                mMainIvHome.setImageResource(R.mipmap.ac1);
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.main_fl, new FragmentHome());
                transaction1.commit();

                break;
            case R.id.main_iv_class:

                mMainIvClass.setImageResource(R.mipmap.abx);
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.main_fl, new FragmentClass());
                transaction2.commit();

                break;
            case R.id.main_iv_find:

                mMainIvFind.setImageResource(R.mipmap.abz);
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.main_fl, new FragmentFind());
                transaction3.commit();

                break;
            case R.id.main_iv_cart:

                mMainIvCart.setImageResource(R.mipmap.abv);
                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                transaction4.replace(R.id.main_fl, new FragmentCart());
                transaction4.commit();

                break;
            case R.id.main_iv_my:

                mMainIvMy.setImageResource(R.mipmap.ac3);
                FragmentTransaction transaction5 = getSupportFragmentManager().beginTransaction();
                transaction5.replace(R.id.main_fl, new FragmentMy());
                transaction5.commit();

                break;
        }
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==2000){
        String uid = data.getStringExtra("uid");
        Toast.makeText(MainActivity.this,uid,Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);

    }*/

}
