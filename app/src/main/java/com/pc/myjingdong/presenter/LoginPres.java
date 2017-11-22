package com.pc.myjingdong.presenter;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.LoginBean;
import com.pc.myjingdong.model.LoginModel;
import com.pc.myjingdong.utils.OnNetListener;
import com.pc.myjingdong.view.LoginView;

/**
 * Created by pc on 2017/11/2.
 */

public class LoginPres {
    LoginView loginView;
    private final LoginModel loginModel;

    public LoginPres(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void loginGL(String user,String pwd){
        loginModel.getLoginData(user, pwd, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                loginView.loginData((LoginBean) baseBean);
            }
        });
    }

}
