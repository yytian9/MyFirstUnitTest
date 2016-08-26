package com.example.yytian.simplemocktest.login;

import android.os.Handler;
import android.os.Looper;

/**
 * 创建者：     yytian
 * 创建时间：   16-8-21 下午3:06
 * 描述：
 */
public class LoginManager {
    private Handler mUiHandler = new Handler(Looper.getMainLooper());
    public void login(final String usrName, final String password, final LoginCallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                super.run();
//                SystemClock.sleep(3000);
                mUiHandler.post(new Runnable() {
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

    public  interface LoginCallBack {
        void loginSucessed();
        void loginFailed();
    }
}
