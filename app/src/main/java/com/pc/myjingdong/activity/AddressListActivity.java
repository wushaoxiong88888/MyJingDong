package com.pc.myjingdong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pc.myjingdong.R;
import com.pc.myjingdong.adapter.AddressListRlvAdapter;
import com.pc.myjingdong.bean.FindAddressBean;
import com.pc.myjingdong.presenter.FindAddressPres;
import com.pc.myjingdong.view.FindAddressView;

import java.util.List;

public class AddressListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mAddListIvBack;
    private RecyclerView mAddListRlv;
    /**
     * ＋  新建地址
     */
    private Button mAddListBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initView();

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");
        String token = preferences.getString("token", "");
        //关联
        FindAddressPres findAddressPres = new FindAddressPres(new FindAddressView() {
            @Override
            public void findAddShow(FindAddressBean findAddressBean) {
                List<FindAddressBean.DataBean> list = findAddressBean.getData();
                //Toast.makeText(AddressListActivity.this,findAddressBean.getData().get(1).getAddr(),Toast.LENGTH_SHORT).show();
                //给rlc设置上值
                mAddListRlv.setLayoutManager(new LinearLayoutManager(AddressListActivity.this));
                AddressListRlvAdapter adapter = new AddressListRlvAdapter(AddressListActivity.this, list);
                mAddListRlv.setAdapter(adapter);
            }
        });
        findAddressPres.findAddressMandV(uid,token);

    }

    private void initView() {
        mAddListIvBack = (ImageView) findViewById(R.id.add_list_iv_back);
        mAddListIvBack.setOnClickListener(this);
        mAddListRlv = (RecyclerView) findViewById(R.id.add_list_rlv);
        mAddListBt = (Button) findViewById(R.id.add_list_bt);
        mAddListBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_list_iv_back:
                finish();
                break;
            case R.id.add_list_bt:

                Intent intent =new Intent(AddressListActivity.this,AddAddressActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }
}
