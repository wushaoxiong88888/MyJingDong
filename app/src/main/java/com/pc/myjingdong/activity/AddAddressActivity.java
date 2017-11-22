package com.pc.myjingdong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.presenter.AddAddressPres;
import com.pc.myjingdong.view.AddAddressView;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mAddAddIvBack;
    /**
     * 请输入详细地址
     */
    private EditText mAddAddEtAddr;
    /**
     * 请输入手机号
     */
    private EditText mAddAddEtPhone;
    /**
     * 请输入姓名
     */
    private EditText mAddAddEtName;
    /**
     * 确认新建
     */
    private Button mAddAddBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
    }

    private void initView() {
        mAddAddIvBack = (ImageView) findViewById(R.id.add_add_iv_back);
        mAddAddIvBack.setOnClickListener(this);
        mAddAddEtAddr = (EditText) findViewById(R.id.add_add_et_addr);
        mAddAddEtPhone = (EditText) findViewById(R.id.add_add_et_phone);
        mAddAddEtName = (EditText) findViewById(R.id.add_add_et_name);
        mAddAddBt = (Button) findViewById(R.id.add_add_bt);
        mAddAddBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_add_iv_back:
                finish();
                break;
            case R.id.add_add_bt:

                String addr = mAddAddEtAddr.getText().toString().trim();
                String phone = mAddAddEtPhone.getText().toString().trim();
                String name = mAddAddEtName.getText().toString().trim();

                SharedPreferences pre = getSharedPreferences("user", MODE_PRIVATE);
                String uid = pre.getString("uid", "");
                String token = pre.getString("token", "");

                //关联
                AddAddressPres addAddressPres = new AddAddressPres(new AddAddressView() {
                    @Override
                    public void addaddrShow(BaseBean baseBean) {
                        Toast.makeText(AddAddressActivity.this,baseBean.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                });
                addAddressPres.addAddMandV(uid,addr,phone,name,token);

                Intent intent = new Intent(AddAddressActivity.this,AddressListActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }
}
