package com.example.yytian.simplemocktest.login;

import com.example.yytian.simplemocktest.BasePersenter;
import com.example.yytian.simplemocktest.BaseView;

/**
 * 创建者：     yytian
 * 创建时间：   16-8-19 下午3:54
 * 描述：
 */
public interface LoginContact  {
    interface Presenter extends BasePersenter {
        void login(String usrName,String password);
    }


    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);
        void showLoginResult(String meg);
        void showErrorView();
        void showSuccessView();
    }
}
