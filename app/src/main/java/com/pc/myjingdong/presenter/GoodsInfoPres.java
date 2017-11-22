package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.GoodsInfoBean;
import com.pc.myjingdong.model.GoodsInfoModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.GoodsInfoView;

/**
 * Created by pc on 2017/11/8.
 */

public class GoodsInfoPres {
    GoodsInfoView goodsInfoView;
    private final GoodsInfoModel goodsInfoModel;

    public GoodsInfoPres(GoodsInfoView goodsInfoView) {
        this.goodsInfoView = goodsInfoView;
        goodsInfoModel = new GoodsInfoModel();
    }

    public void infoMandV(String pid){
        goodsInfoModel.infoGetData(pid, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                goodsInfoView.infoShow((GoodsInfoBean) baseBean);
            }
        });
    }

}
