package com.example.yytian.simplemocktest.login;

/**
 * 创建者：     yytian
 * 创建时间：   16-8-19 下午3:51
 * 描述：
 */
public class LoginPresenter implements LoginContact.Presenter {

    private LoginContact.View loginView;
    private LoginManager mLoginManager;

    public LoginPresenter(LoginContact.View loginView,LoginManager loginManager) {
        this.loginView = loginView;
        mLoginManager = loginManager;
    }

    @Override
    public void start() {

    }

    @Override
    public void login(final String usrName, final String password) {
        //0.先是进行本地验证

        if (isNull(usrName)||isNull(password)) {
            loginView.showErrorView();
            return;
        }

        //1.首先是登录，然后再在回调中完view的相关操作
        loginView.setLoadingIndicator(true);


        mLoginManager.login(usrName, password, new LoginManager.LoginCallBack() {
            @Override
            public void loginSucessed() {

                //显示登录结果
                loginView.showLoginResult("登录成功");
                loginView.setLoadingIndicator(false);
            }

            @Override
            public void loginFailed() {
                loginView.showSuccessView();
                //显示登录结果
                loginView.showLoginResult("登录失败");
                loginView.setLoadingIndicator(false);
            }
        });

    }

    private boolean isNull(String str){
        if(str==null || str.length()==0){
            return true;
        }
        return false;
    }
}
