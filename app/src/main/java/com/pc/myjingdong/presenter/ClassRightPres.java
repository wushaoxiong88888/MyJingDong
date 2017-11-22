package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.ClassRightBean;
import com.pc.myjingdong.model.ClassRightModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.ClassRightView;

/**
 * Created by pc on 2017/11/7.
 */

public class ClassRightPres {
    ClassRightView classRightView;
    private final ClassRightModel classRightModel;

    public ClassRightPres(ClassRightView classRightView) {
        this.classRightView = classRightView;
        classRightModel = new ClassRightModel();
    }

    public void classRightMandV(int cid){
        classRightModel.classRightGetData(cid, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                classRightView.classRightShow((ClassRightBean) baseBean);
            }
        });
    }

}
