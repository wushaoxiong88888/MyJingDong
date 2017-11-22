package com.pc.myjingdong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pc.myjingdong.R;
import com.pc.myjingdong.bean.LoginBean;
import com.pc.myjingdong.bean.Person;
import com.pc.myjingdong.gen.DaoMaster;
import com.pc.myjingdong.gen.DaoSession;
import com.pc.myjingdong.gen.PersonDao;
import com.pc.myjingdong.presenter.LoginPres;
import com.pc.myjingdong.presenter.UploadPres;
import com.pc.myjingdong.view.LoginView;
import com.pc.myjingdong.view.UploadView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * ×
     */
    private TextView mLoginTvBack;
    /**
     * 用户名/邮箱/密码
     */
    private EditText mLoginEtUser;
    /**
     * 请输入密码
     */
    private EditText mLoginEtPwd;
    /**
     * 登录
     */
    private Button mLoginBtLogin;
    /**
     * 快速注册
     */
    private TextView mLoginTvRegister;
    private SharedPreferences preferences;

    //数据库
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private PersonDao mPersonDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        //初始化数据库
        openDb();

        //得到管理者
        preferences = getSharedPreferences("user", MODE_PRIVATE);

        //判断
        boolean have = preferences.getBoolean("have", false);
        if (have) {
            String username = preferences.getString("username", "");
            mLoginEtUser.setText(username);
        }

    }

    //初始化数据库
    private void openDb() {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(this, "person.db", null);
        mDaoMaster = new DaoMaster(mDevOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
        mPersonDao = mDaoSession.getPersonDao();
    }

    //数据库插入
    public void insert(Long uid, String username, String pwd) {
        Person person = new Person(uid, username, pwd);
        mPersonDao.insert(person);
    }

    private void initView() {
        mLoginTvBack = (TextView) findViewById(R.id.login_tv_back);
        mLoginTvBack.setOnClickListener(this);
        mLoginEtUser = (EditText) findViewById(R.id.login_et_user);
        mLoginEtPwd = (EditText) findViewById(R.id.login_et_pwd);
        mLoginBtLogin = (Button) findViewById(R.id.login_bt_login);
        mLoginBtLogin.setOnClickListener(this);
        mLoginTvRegister = (TextView) findViewById(R.id.login_tv_register);
        mLoginTvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_tv_back:
                finish();
                break;
            case R.id.login_bt_login:
                //获取输入框的值
                String user = mLoginEtUser.getText().toString().trim();
                final String pwd = mLoginEtPwd.getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "输入有误,请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    LoginPres loginPres = new LoginPres(new LoginView() {
                        @Override
                        public void loginData(LoginBean loginBean) {
                            //LoginBean loginBean = (LoginBean) baseBean;
                            String msg = loginBean.getMsg();
                            Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
                            //loginBean.getData().setNickname("武少雄88888");

                            int uid = loginBean.getData().getUid();
                            String username = (String) loginBean.getData().getMobile();
                            String token = (String) loginBean.getData().getToken();
                            Log.e("TAG----", username);

                            //把uid等信息存进preferences里
                            SharedPreferences.Editor edit = preferences.edit();
                            edit.putString("uid", String.valueOf(uid));
                            edit.putString("username", username);
                            edit.putString("token", token);
                            edit.putBoolean("have", true);
                            edit.commit();


                            //String token = preferences.getString("token", "");

                            //上传头像
                            UploadPres uploadPres = new UploadPres(new UploadView() {
                                @Override
                                public void uploadData(LoginBean loginBean) {
                                    Toast.makeText(LoginActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            uploadPres.uploadMadnV(String.valueOf(uid),token);


                            Log.e("TAG-----", uid + "");
                            Intent intent = new Intent();
                            intent.putExtra("uid", String.valueOf(uid));
                            intent.putExtra("username", username);
                            //intent.putExtra("token",token);
                            setResult(2000, intent);
                            finish();

                            //存进数据库
                            //insert(Long.valueOf(uid), username, pwd);

                        }
                    });
                    loginPres.loginGL(user, pwd);

                    //输入框内容存进preferences里
                    /*SharedPreferences.Editor edit = preferences.edit();
                    //edit.putString("username",user);
                    edit.putBoolean("panDuan",true);
                    edit.commit();*/

                }

                break;
            case R.id.login_tv_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}
