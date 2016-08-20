package com.example.yytian.myfirstunittest.login;

import com.example.yytian.myfirstunittest.BasePersenter;
import com.example.yytian.myfirstunittest.BaseView;

/**
 * 创建者：     yytian
 * 创建时间：   16-8-19 下午3:54
 * 描述：
 */
public interface LoginContact  {
    interface Presenter extends BasePersenter {

    }


    interface View extends BaseView<Presenter>{

    }
}
