package com.pc.myjingdong.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pc.myjingdong.R;
import com.pc.myjingdong.adapter.GoodsListAdapter;
import com.pc.myjingdong.bean.GoodsListBean;
import com.pc.myjingdong.presenter.GoodsListPres;
import com.pc.myjingdong.view.GoodsListView;

import java.util.ArrayList;
import java.util.List;

public class GoodsListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mListBack;
    private ImageView mListIv;
    /**
     * 综合
     */
    private TextView mListTvSum;
    /**
     * 销量
     */
    private TextView mListTvSale;
    /**
     * 价格
     */
    private TextView mListTvPrice;
    private LinearLayout mListLlChoose;
    private XRecyclerView mListRlv;
    private String pscid;
    private GoodsListPres goodsListPres;
    private List<GoodsListBean.DataBean> data = new ArrayList<>();
    private int count = 1;
    private boolean boo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initView();

        //得到传过来的pscid
        Intent intent = getIntent();
        pscid = intent.getStringExtra("pscid");

        //显示默认rlv
        rlvQie(true);

        //不能刷新
        mListRlv.setPullRefreshEnabled(false);
        //上拉加载更多
        mListRlv.setLoadingMoreEnabled(true);
        mListRlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                count++;
                Toast.makeText(GoodsListActivity.this, "第" + count + "页", Toast.LENGTH_SHORT).show();
                goodsListPres.listMandV(pscid, count, 0);
                mListRlv.loadMoreComplete();
                if (count > 2) {
                    mListRlv.setLoadingMoreEnabled(false);
                    Toast.makeText(GoodsListActivity.this, "到底了", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //rlv布局切换
    public void rlvQie(final boolean flag) {

        goodsListPres = new GoodsListPres(new GoodsListView() {
            @Override
            public void listShow(GoodsListBean listBean) {
                //Toast.makeText(GoodsListActivity.this, listBean.getData().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                List<GoodsListBean.DataBean> list = listBean.getData();
                //添加到新集合中去
                data.addAll(list);
                //显示rlv方法
                RecyclerView.LayoutManager layoutManager = null;

                if (flag) {
                    layoutManager = new LinearLayoutManager(GoodsListActivity.this);
                } else {
                    layoutManager = new GridLayoutManager(GoodsListActivity.this, 2);
                }
                mListRlv.setLayoutManager(layoutManager);
                //mListRlv.setLayoutManager(new LinearLayoutManager(GoodsListActivity.this));
                GoodsListAdapter adapter = new GoodsListAdapter(GoodsListActivity.this, data);
                mListRlv.setAdapter(adapter);
                //点击跳转
                adapter.setOnItemListener3(new GoodsListAdapter.OnItemListener3() {
                    @Override
                    public void OnItemCilck(GoodsListBean.DataBean dataBean) {
                        //得到id传过去
                        int pid = dataBean.getPid();

                        Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                        intent.putExtra("pid", String.valueOf(pid));
                        startActivity(intent);
                    }
                });
            }
        });
        goodsListPres.listMandV(pscid, count, 0);
    }

    //文字回复无颜色
    public void textNoColor() {
        mListTvSum.setTextColor(Color.parseColor("#000000"));
        mListTvSale.setTextColor(Color.parseColor("#000000"));
        mListTvPrice.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_back:
                finish();
                break;
            case R.id.list_iv:
                if (boo==false) {
                    rlvQie(false);
                    boo = true;
                } else {
                    rlvQie(true);
                    boo = false;
                }
                break;
            case R.id.list_tv_sum:
                textNoColor();
                data.clear();
                goodsListPres.listMandV(pscid, count, 0);
                mListTvSum.setTextColor(Color.parseColor("#ff3660"));
                break;
            case R.id.list_tv_sale:
                textNoColor();
                data.clear();
                goodsListPres.listMandV(pscid, count, 1);
                mListTvSale.setTextColor(Color.parseColor("#ff3660"));
                break;
            case R.id.list_tv_price:
                textNoColor();
                data.clear();
                goodsListPres.listMandV(pscid, count, 2);
                mListTvPrice.setTextColor(Color.parseColor("#ff3660"));
                break;
            case R.id.list_ll_choose:
                break;
        }
    }

    private void initView() {
        mListBack = (ImageView) findViewById(R.id.list_back);
        mListBack.setOnClickListener(this);
        mListIv = (ImageView) findViewById(R.id.list_iv);
        mListIv.setOnClickListener(this);
        mListTvSum = (TextView) findViewById(R.id.list_tv_sum);
        mListTvSum.setOnClickListener(this);
        mListTvSale = (TextView) findViewById(R.id.list_tv_sale);
        mListTvSale.setOnClickListener(this);
        mListTvPrice = (TextView) findViewById(R.id.list_tv_price);
        mListTvPrice.setOnClickListener(this);
        mListLlChoose = (LinearLayout) findViewById(R.id.list_ll_choose);
        mListLlChoose.setOnClickListener(this);
        mListRlv = (XRecyclerView) findViewById(R.id.list_rlv);
    }


}
