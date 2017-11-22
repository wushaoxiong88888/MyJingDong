package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.ClassLeftBean;
import com.pc.myjingdong.model.ClassLeftModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.ClassLeftView;

/**
 * Created by pc on 2017/11/6.
 */

public class ClassLeftPres {
    ClassLeftView classLeftView;
    private final ClassLeftModel classLeftModel;

    public ClassLeftPres(ClassLeftView classLeftView) {
        this.classLeftView = classLeftView;
        classLeftModel = new ClassLeftModel();
    }

    public void classLeftMandV(){
        classLeftModel.classLeftGetData(new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                classLeftView.classLeftShow((ClassLeftBean) baseBean);
            }
        });
    }

}
