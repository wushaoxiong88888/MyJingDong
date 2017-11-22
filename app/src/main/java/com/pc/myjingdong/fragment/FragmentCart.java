package com.pc.myjingdong.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.pc.myjingdong.R;
import com.pc.myjingdong.activity.ConfirmOrderActivity;
import com.pc.myjingdong.adapter.CartRlvAdapter;
import com.pc.myjingdong.bean.FindCartBean;
import com.pc.myjingdong.presenter.FindCartPres;
import com.pc.myjingdong.utils.MessageEvent;
import com.pc.myjingdong.view.FindCartView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by pc on 2017/10/31.
 */

public class FragmentCart extends Fragment implements View.OnClickListener {
    private View view;
    private RecyclerView mCartRlv;
    private SwipeRefreshLayout mCartSpl;
    /**
     * 全选
     */
    public static CheckBox mCartCbAll;
    /**
     * 合计:
     */
    public static TextView mCartTvCount;
    /**
     * 去结算(0)
     */
    private Button mCartBtPay;
    public static CartRlvAdapter adapter;

    private double price;
    private String title;
    private String images;
    private List<FindCartBean.DataBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_cart, container, false);

        EventBus.getDefault().register(this);

        initView(view);

        SharedPreferences preferences = getActivity().getSharedPreferences("user", getContext().MODE_PRIVATE);
        boolean have = preferences.getBoolean("have", false);
        if (have) {
            String uid = preferences.getString("uid", "");
            String token = preferences.getString("token", "");

            FindCartPres findCartPres = new FindCartPres(new FindCartView() {
                @Override
                public void findcartShow(FindCartBean findCartBean) {

                    for (int i = 0; i < findCartBean.getData().size(); i++) {
                        findCartBean.getData().get(i).setCheckFather(false);
                        for (int j = 0; j < findCartBean.getData().get(i).getList().size(); j++) {
                            findCartBean.getData().get(i).getList().get(j).setCheckChild(false);
                        }
                    }

                    list = findCartBean.getData();

                    //给rlv设置上去
                    mCartRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter = new CartRlvAdapter(getActivity(), list);
                    mCartRlv.setAdapter(adapter);
                }
            });
            findCartPres.cartMandV(uid, token);
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }


        return view;
    }

    private void initView(View view) {
        mCartRlv = (RecyclerView) view.findViewById(R.id.cart_rlv);
        mCartSpl = (SwipeRefreshLayout) view.findViewById(R.id.cart_spl);
        mCartCbAll = (CheckBox) view.findViewById(R.id.cart_cbAll);
        mCartCbAll.setOnClickListener(this);
        mCartTvCount = (TextView) view.findViewById(R.id.cart_tv_count);
        mCartBtPay = (Button) view.findViewById(R.id.cart_bt_pay);
        mCartBtPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_cbAll:
                adapter.Allchecked(mCartCbAll.isChecked());
                break;
            case R.id.cart_bt_pay:

                String s = mCartTvCount.getText().toString();

                if (s.equals("0元")) {
                    Toast.makeText(getActivity(), "请先选择商品", Toast.LENGTH_SHORT).show();
                } else {

                    for (int i = 0; i < list.size(); i++) {
                        List<FindCartBean.DataBean.ListBean> data = this.list.get(i).getList();
                        for (int j = 0; j < data.size(); j++) {
                            boolean checkChild = data.get(j).isCheckChild();
                            if(checkChild){
                                images = data.get(j).getImages();
                                title = data.get(j).getTitle();
                                price = data.get(j).getPrice();
                            }
                        }
                    }

                    String AllPrice = mCartTvCount.getText().toString().trim();

                    Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                    intent.putExtra("images",images);
                    intent.putExtra("title",title);
                    intent.putExtra("price",String.valueOf(price));
                    intent.putExtra("AllPrice", AllPrice);
                    startActivity(intent);

                    break;
                }
        }
    }

    //接收EventBus的消息
    @Subscribe
    public void OnMessageEvent(MessageEvent msg) {
        mCartCbAll.setChecked(msg.isChecked());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
