package com.pc.myjingdong.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.pc.myjingdong.R;
import com.pc.myjingdong.fragment.FragmentCart;

public class CartActivity extends AppCompatActivity {

    private FrameLayout mFl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction replace = manager.beginTransaction().replace(R.id.fl, new FragmentCart());
        replace.commit();

    }

    private void initView() {
        mFl = (FrameLayout) findViewById(R.id.fl);
    }
}
