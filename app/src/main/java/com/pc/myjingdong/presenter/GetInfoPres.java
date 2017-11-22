package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.GetUserInfoBean;
import com.pc.myjingdong.model.GetInfoModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.GetInfoView;

/**
 * Created by pc on 2017/11/3.
 */

public class GetInfoPres {

    GetInfoView getInfoView;
    private final GetInfoModel getInfoModel;

    public GetInfoPres(GetInfoView getInfoView) {
        this.getInfoView = getInfoView;
        getInfoModel = new GetInfoModel();
    }

    public void getinfoMandV(String uid,String token){
        getInfoModel.getuserData(uid,token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                getInfoView.getinfoData((GetUserInfoBean) baseBean);
            }
        });
    }

}
