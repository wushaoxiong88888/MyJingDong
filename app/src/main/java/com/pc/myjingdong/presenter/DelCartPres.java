package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.model.DelCartModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.DelCartView;

/**
 * Created by pc on 2017/11/13.
 */

public class DelCartPres {
    DelCartView delCartView;
    private final DelCartModel delCartModel;

    public DelCartPres(DelCartView delCartView) {
        this.delCartView = delCartView;
        delCartModel = new DelCartModel();
    }

    public void delMandV(String uid,String pid,String token){
        delCartModel.delGetData(uid, pid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                delCartView.delCartShow(baseBean);
            }
        });
    }

}
