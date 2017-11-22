package com.pc.myjingdong.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pc.myjingdong.R;
import com.pc.myjingdong.activity.LoginActivity;
import com.pc.myjingdong.activity.ShowUserActivity;
import com.pc.myjingdong.bean.GetUserInfoBean;
import com.pc.myjingdong.bean.LoginBean;
import com.pc.myjingdong.presenter.GetInfoPres;
import com.pc.myjingdong.presenter.UploadPres;
import com.pc.myjingdong.view.GetInfoView;
import com.pc.myjingdong.view.UploadView;

/**
 * Created by pc on 2017/10/31.
 */

public class FragmentMy extends Fragment implements View.OnClickListener {
    private View view;
    private LinearLayout mFragMyLogin;
    /**
     * 登录/注册
     */
    private TextView mFragMyTv;
    private SimpleDraweeView mFragMySdv;
    private SharedPreferences preferences;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_my, container, false);

        initView(view);

        preferences = getActivity().getSharedPreferences("user", getContext().MODE_PRIVATE);
        token = preferences.getString("token", "");

        boolean have = preferences.getBoolean("have", false);
        if (have) {
            String uid = preferences.getString("uid", "");
            String username = preferences.getString("username", "");
            //获取用户信息
            GetInfoPres getInfoPres = new GetInfoPres(new GetInfoView() {
                @Override
                public void getinfoData(GetUserInfoBean getUserInfoBean) {
                    Toast.makeText(getActivity(), getUserInfoBean.getMsg(), Toast.LENGTH_SHORT).show();
                    String icon = getUserInfoBean.getData().getIcon();
                    //给头像设置图片
                    Uri uri = Uri.parse(icon);
                    mFragMySdv.setImageURI(uri);
                }
            });
            getInfoPres.getinfoMandV(uid,token);
            mFragMyTv.setText(username);

        }

        return view;
    }

    private void initView(View view) {
        mFragMyLogin = (LinearLayout) view.findViewById(R.id.frag_my_login);
        mFragMyLogin.setOnClickListener(this);
        mFragMyTv = (TextView) view.findViewById(R.id.frag_my_tv);
        mFragMySdv = (SimpleDraweeView) view.findViewById(R.id.frag_my_sdv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_my_login:

                boolean have = preferences.getBoolean("have", false);
                if (have) {
                    Intent intent = new Intent(getActivity(), ShowUserActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1000);
                }


                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 2000) {
            //这个id和username是登录成功后获取的,也就是登录bean对象里的属性值
            String uid = data.getStringExtra("uid");
            String username = data.getStringExtra("username");
            //Toast.makeText(getActivity(),uid,Toast.LENGTH_SHORT).show();

            String token = preferences.getString("token", "");

            //上传头像
            UploadPres uploadPres = new UploadPres(new UploadView() {
                @Override
                public void uploadData(LoginBean loginBean) {
                    Toast.makeText(getActivity(), loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            });
            //uploadPres.uploadMadnV(uid,token);
            //设置值
            mFragMyTv.setText(username);

            //获取用户信息
            GetInfoPres getInfoPres = new GetInfoPres(new GetInfoView() {
                @Override
                public void getinfoData(GetUserInfoBean getUserInfoBean) {
                    Toast.makeText(getActivity(), getUserInfoBean.getMsg(), Toast.LENGTH_SHORT).show();
                    String icon = getUserInfoBean.getData().getIcon();
                    //给头像设置图片
                    Uri uri = Uri.parse(icon);
                    mFragMySdv.setImageURI(uri);
                }
            });
            getInfoPres.getinfoMandV(uid,token);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
