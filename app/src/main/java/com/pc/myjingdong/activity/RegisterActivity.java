package com.pc.myjingdong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.presenter.RegPres;
import com.pc.myjingdong.view.RegView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * ×
     */
    private TextView mRegTvBack;
    /**
     * 用户名/邮箱/密码
     */
    private EditText mRegEtUser;
    /**
     * 请输入密码
     */
    private EditText mRegEtPwd;
    /**
     * 登录
     */
    private Button mRegBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mRegTvBack = (TextView) findViewById(R.id.reg_tv_back);
        mRegTvBack.setOnClickListener(this);
        mRegEtUser = (EditText) findViewById(R.id.reg_et_user);
        mRegEtPwd = (EditText) findViewById(R.id.reg_et_pwd);
        mRegBtLogin = (Button) findViewById(R.id.reg_bt_login);
        mRegBtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_tv_back:
                finish();
                break;
            case R.id.reg_bt_login:

                //获取输入框的值
                String name = mRegEtUser.getText().toString().trim();
                String pwd = mRegEtPwd.getText().toString().trim();

                //关联
                RegPres regPres = new RegPres(new RegView() {
                    @Override
                    public void regShow(BaseBean baseBean) {
                        Toast.makeText(RegisterActivity.this, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
                regPres.regMandV(name,pwd);

                finish();
                break;
        }
    }
}
