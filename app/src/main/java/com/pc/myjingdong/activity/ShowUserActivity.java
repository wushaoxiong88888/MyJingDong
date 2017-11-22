package com.pc.myjingdong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.GetUserInfoBean;
import com.pc.myjingdong.presenter.GetInfoPres;
import com.pc.myjingdong.view.GetInfoView;

public class ShowUserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mShowIv;
    private SimpleDraweeView mShowSdv;
    /**
     * 昵称
     */
    private TextView mShowTvName;
    /**
     * 退出登录
     */
    private Button mShowExit;
    private SharedPreferences preferences;
    private LinearLayout mShowLlAddress;
    /**
     * 手机号
     */
    private TextView mShowTvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        initView();

        preferences = getSharedPreferences("user", MODE_PRIVATE);
        boolean have = preferences.getBoolean("have", false);
        if (have) {
            String uid = preferences.getString("uid", "");
            String username = preferences.getString("username", "");
            String token = preferences.getString("token", "");
            //获取用户信息
            GetInfoPres getInfoPres = new GetInfoPres(new GetInfoView() {
                @Override
                public void getinfoData(GetUserInfoBean getUserInfoBean) {
                    Toast.makeText(ShowUserActivity.this, getUserInfoBean.getMsg(), Toast.LENGTH_SHORT).show();
                    String icon = getUserInfoBean.getData().getIcon();
                    //给头像设置图片
                    Uri uri = Uri.parse(icon);
                    mShowSdv.setImageURI(uri);

                    //手机号
                    String phone = getUserInfoBean.getData().getMobile();
                    mShowTvPhone.setText(phone);
                }
            });
            getInfoPres.getinfoMandV(uid, token);
            mShowTvName.setText(username);

        }

    }

    private void initView() {
        mShowIv = (ImageView) findViewById(R.id.show_iv);
        mShowIv.setOnClickListener(this);
        mShowSdv = (SimpleDraweeView) findViewById(R.id.show_sdv);
        mShowTvName = (TextView) findViewById(R.id.show_tv_name);
        mShowExit = (Button) findViewById(R.id.show_exit);
        mShowExit.setOnClickListener(this);
        mShowLlAddress = (LinearLayout) findViewById(R.id.show_ll_address);
        mShowLlAddress.setOnClickListener(this);
        mShowTvPhone = (TextView) findViewById(R.id.show_tv_phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_iv:
                finish();
                break;
            case R.id.show_exit:

                preferences.edit().clear().commit();
                Intent intent = new Intent(ShowUserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;
            case R.id.show_ll_address:

                Intent intent2 = new Intent(ShowUserActivity.this, AddressListActivity.class);
                startActivity(intent2);

                break;
        }
    }
}
