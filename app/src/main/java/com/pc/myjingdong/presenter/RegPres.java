package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.model.RegModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.RegView;

/**
 * Created by pc on 2017/11/21.
 */

public class RegPres {
    RegView regView;
    private final RegModel regModel;

    public RegPres(RegView regView) {
        this.regView = regView;
        regModel = new RegModel();
    }
    public void regMandV(String mobile, String password){
        regModel.getRegDta(mobile, password, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                regView.regShow(baseBean);
            }
        });
    }
}
