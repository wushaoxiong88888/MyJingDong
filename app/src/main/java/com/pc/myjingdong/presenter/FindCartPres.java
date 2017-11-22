package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.FindCartBean;
import com.pc.myjingdong.model.FindCartModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.FindCartView;

/**
 * Created by pc on 2017/11/9.
 */

public class FindCartPres {
    FindCartView findCartView;
    private final FindCartModel findCartModel;

    public FindCartPres(FindCartView findCartView) {
        this.findCartView = findCartView;
        findCartModel = new FindCartModel();
    }

    public void cartMandV(String uid,String token){
        findCartModel.cartGetData(uid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                findCartView.findcartShow((FindCartBean) baseBean);
            }
        });
    }

}
