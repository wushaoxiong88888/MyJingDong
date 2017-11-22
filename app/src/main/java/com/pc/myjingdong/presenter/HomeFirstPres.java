package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.HomeFirstBean;
import com.pc.myjingdong.model.HomeFirstModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.HomeFirstView;

/**
 * Created by pc on 2017/11/3.
 */

public class HomeFirstPres {
    HomeFirstView homeFirstView;
    private final HomeFirstModel homeFirstModel;

    public HomeFirstPres(HomeFirstView homeFirstView) {
        this.homeFirstView = homeFirstView;
        homeFirstModel = new HomeFirstModel();
    }

    public void homeFistMandV(){
        homeFirstModel.getFirstData(new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                homeFirstView.showFirstData((HomeFirstBean) baseBean);
            }
        });
    }

}
