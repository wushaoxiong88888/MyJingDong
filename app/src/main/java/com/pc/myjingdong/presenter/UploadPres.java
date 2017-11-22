package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.LoginBean;
import com.pc.myjingdong.model.UploadModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.UploadView;

/**
 * Created by pc on 2017/11/2.
 */

public class UploadPres {

    UploadView uploadView;
    private final UploadModel uploadModel;

    public UploadPres(UploadView uploadView) {
        this.uploadView = uploadView;
        uploadModel = new UploadModel();
    }

    public void uploadMadnV(String uid,String token){
        uploadModel.getUploadData(uid,token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                uploadView.uploadData((LoginBean) baseBean);
            }
        });
    }

}
