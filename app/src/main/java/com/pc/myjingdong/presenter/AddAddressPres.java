package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.model.AddAddressModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.AddAddressView;

/**
 * Created by pc on 2017/11/15.
 */

public class AddAddressPres {
    AddAddressView addAddressView;
    private final AddAddressModel addAddressModel;

    public AddAddressPres(AddAddressView addAddressView) {
        this.addAddressView = addAddressView;
        addAddressModel = new AddAddressModel();
    }

    public void addAddMandV(String uid, String addr, String phone, String name, String token){
        addAddressModel.addaddrgetData(uid, addr, phone, name, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                addAddressView.addaddrShow(baseBean);
            }
        });
    }
}
