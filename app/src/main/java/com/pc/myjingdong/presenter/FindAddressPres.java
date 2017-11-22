package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.FindAddressBean;
import com.pc.myjingdong.model.FindAddressModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.FindAddressView;

/**
 * Created by pc on 2017/11/15.
 */

public class FindAddressPres {
    FindAddressView findAddressView;
    private final FindAddressModel findAddressModel;

    public FindAddressPres(FindAddressView findAddressView) {
        this.findAddressView = findAddressView;
        findAddressModel = new FindAddressModel();
    }
    public void findAddressMandV(String uid,String token){
        findAddressModel.getFindAddData(uid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                findAddressView.findAddShow((FindAddressBean) baseBean);
            }
        });
    }
}
