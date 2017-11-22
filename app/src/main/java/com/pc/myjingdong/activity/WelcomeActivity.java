package com.pc.myjingdong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.pc.myjingdong.R;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView mWelIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }

    private void initView() {
        mWelIv = (ImageView) findViewById(R.id.wel_iv);
    }
}
