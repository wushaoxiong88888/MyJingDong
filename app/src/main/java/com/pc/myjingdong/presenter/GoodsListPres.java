package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.GoodsListBean;
import com.pc.myjingdong.model.GoodsListModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.GoodsListView;

/**
 * Created by pc on 2017/11/7.
 */

public class GoodsListPres {
    GoodsListView goodsListView;
    private final GoodsListModel goodsListModel;

    public GoodsListPres(GoodsListView goodsListView) {
        this.goodsListView = goodsListView;
        goodsListModel = new GoodsListModel();
    }

    public void listMandV(String pscid, int page, int sort) {
        goodsListModel.listGetData(pscid, page, sort, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                goodsListView.listShow((GoodsListBean) baseBean);
            }
        });
    }

}
