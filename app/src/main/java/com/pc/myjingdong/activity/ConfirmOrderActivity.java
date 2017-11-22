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
import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.FindAddressBean;
import com.pc.myjingdong.presenter.CreatePres;
import com.pc.myjingdong.presenter.FindAddressPres;
import com.pc.myjingdong.view.CreateView;
import com.pc.myjingdong.view.FindAddressView;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mConfirmIvBack;
    /**
     * 姓名
     */
    private TextView mConfirmAddressName;
    /**
     * 手机号
     */
    private TextView mConfirmAddressPhone;
    /**
     * 地址
     */
    private TextView mConfirmAddressInfo;
    private LinearLayout mConfirmAddress;
    private SimpleDraweeView mConfirmSdv;
    /**
     * 商品信息
     */
    private TextView mConfirmTvGoodsInfo;
    /**
     * 商品信息
     */
    private TextView mConfirmTvGoodsPrice;
    /**
     * 实付款:
     */
    private TextView mConfirmTruePrice;
    /**
     * 提交订单
     */
    private Button mConfirmBtCommit;
    private String allPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();

        //得到传过来的值
        Intent intent = getIntent();
        allPrice = intent.getStringExtra("AllPrice");
        String images = intent.getStringExtra("images");
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");

        String[] imgs = images.split("\\|");
        String s = imgs[0].toString();
        //设置上
        mConfirmTruePrice.setText(allPrice);
        mConfirmSdv.setImageURI(Uri.parse(s));
        mConfirmTvGoodsInfo.setText(title);
        mConfirmTruePrice.setText(price);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");
        String token = preferences.getString("token", "");

        //关联
        FindAddressPres findAddressPres = new FindAddressPres(new FindAddressView() {
            @Override
            public void findAddShow(FindAddressBean findAddressBean) {
                //Toast.makeText(ConfirmOrderActivity.this,findAddressBean.getMsg(),Toast.LENGTH_SHORT).show();
                String name = findAddressBean.getData().get(0).getName();
                long mobile = findAddressBean.getData().get(0).getMobile();
                String addr = findAddressBean.getData().get(0).getAddr();

                //设置值给地址
                mConfirmAddressName.setText(name);
                mConfirmAddressPhone.setText(String.valueOf(mobile));
                mConfirmAddressInfo.setText(addr);
            }
        });
        findAddressPres.findAddressMandV(uid,token);

    }

    private void initView() {
        mConfirmIvBack = (ImageView) findViewById(R.id.confirm_iv_back);
        mConfirmIvBack.setOnClickListener(this);
        mConfirmAddressName = (TextView) findViewById(R.id.confirm_address_name);
        mConfirmAddressPhone = (TextView) findViewById(R.id.confirm_address_phone);
        mConfirmAddressInfo = (TextView) findViewById(R.id.confirm_address_info);
        mConfirmAddress = (LinearLayout) findViewById(R.id.confirm_address);
        mConfirmAddress.setOnClickListener(this);
        mConfirmSdv = (SimpleDraweeView) findViewById(R.id.confirm_sdv);
        mConfirmTvGoodsInfo = (TextView) findViewById(R.id.confirm_tv_goods_info);
        mConfirmTvGoodsPrice = (TextView) findViewById(R.id.confirm_tv_goods_price);
        mConfirmTruePrice = (TextView) findViewById(R.id.confirm_true_price);
        mConfirmBtCommit = (Button) findViewById(R.id.confirm_bt_commit);
        mConfirmBtCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_iv_back:
                finish();
                break;
            case R.id.confirm_address:

                Intent i = new Intent(ConfirmOrderActivity.this,AddressListActivity.class);
                startActivityForResult(i,10);

                break;
            case R.id.confirm_bt_commit:

                //String allPrice = mConfirmTvGoodsPrice.getText().toString().trim();

                SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                String uid = user.getString("uid", "");
                String token = user.getString("token", "");

                //关联创建订单
                CreatePres createPres = new CreatePres(new CreateView() {
                    @Override
                    public void createShow(BaseBean baseBean) {
                        Toast.makeText(ConfirmOrderActivity.this,baseBean.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                });
                createPres.createMandV(uid,allPrice,token);

                Intent intent = new Intent(ConfirmOrderActivity.this,PayActivity.class);
                intent.putExtra("allPrice",allPrice);
                startActivity(intent);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
