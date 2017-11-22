package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.model.CreateModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.CreateView;

/**
 * Created by pc on 2017/11/15.
 */

public class CreatePres {
    CreateView createView;
    private final CreateModel createModel;

    public CreatePres(CreateView createView) {
        this.createView = createView;
        createModel = new CreateModel();
    }
    public void createMandV(String uid,String price,String token){
        createModel.createGetData(uid, price, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                createView.createShow(baseBean);
            }
        });
    }
}
