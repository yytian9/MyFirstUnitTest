package com.example.yytian.myfirstunittest.login;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.yytian.myfirstunittest.BaseApplication;


/**
 * 创建者：     yytian
 * 创建时间：   16-8-19 下午3:51
 * 描述：
 */
public class LoginPresenter implements LoginContact.Presenter {

    private LoginContact.View loginView;
    private LoginManager mLoginManager;

    public LoginPresenter(LoginContact.View loginView) {
        this.loginView = loginView;
        mLoginManager = new LoginManager();
    }

    @Override
    public void start() {

    }

    @Override
    public void login(final String usrName, final String password) {
        //0.先是进行本地验证
        if (TextUtils.isEmpty(usrName)) {
            Toast.makeText(BaseApplication.getApplication(), "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(BaseApplication.getApplication(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //1.首先是登录，然后再在回调中完view的相关操作
        loginView.setLoadingIndicator(true);


        mLoginManager.login(usrName, password, new LoginCallBack() {
            @Override
            public void loginSucessed() {
                Toast.makeText(BaseApplication.getApplication(), "登录成功", Toast.LENGTH_SHORT).show();
                //显示登录结果
                loginView.showLoginResult("登录成功");
                loginView.setLoadingIndicator(false);
            }

            @Override
            public void loginFailed() {
                Toast.makeText(BaseApplication.getApplication(), "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                //显示登录结果
                loginView.showLoginResult("登录失败");
                loginView.setLoadingIndicator(false);
            }
        });

    }
}
