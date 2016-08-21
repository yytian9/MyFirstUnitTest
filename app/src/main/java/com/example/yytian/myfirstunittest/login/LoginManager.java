package com.example.yytian.myfirstunittest.login;

import android.os.SystemClock;

import com.example.yytian.myfirstunittest.BaseApplication;

/**
 * 创建者：     yytian
 * 创建时间：   16-8-21 下午3:06
 * 描述：
 */
public class LoginManager {
    public void login(final String usrName, final String password, final LoginCallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(3000);
                BaseApplication.getmMainThreadHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if ("yytian".equals(usrName) && "123".equals(password)) {
                            callBack.loginSucessed();
                        } else {
                            callBack.loginFailed();
                        }

                    }
                });
            }
        }.start();
    }
}
