package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.model.AddCartModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.AddCartView;

/**
 * Created by pc on 2017/11/9.
 */

public class AddCartPres {
    AddCartView addCartView;
    private final AddCartModel addCartModel;

    public AddCartPres(AddCartView addCartView) {
        this.addCartView = addCartView;
        addCartModel = new AddCartModel();
    }

    public void cartMandV(String uid,String pid,String token){
        addCartModel.addCartGetData(uid, pid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                addCartView.addcartShow(baseBean);
            }
        });
    }

}
