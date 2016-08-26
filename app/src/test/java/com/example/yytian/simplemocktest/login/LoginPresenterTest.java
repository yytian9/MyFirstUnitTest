package com.example.yytian.simplemocktest.login;


import com.example.yytian.simplemocktest.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * 创建者：     yytian
 * 创建时间：   16-8-21 下午3:31
 * 描述：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginPresenterTest {
    @Mock
    private LoginManager mLoginManager;

    @Mock
    private LoginContact.View mActivity;

    @Captor
    private ArgumentCaptor<LoginManager.LoginCallBack> mGetTaskCallbackCaptor;
    private LoginPresenter mLoginPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testStart() throws Exception {

    }

    @Test
    public void testLogin() throws Exception {
        mLoginPresenter=new LoginPresenter(mActivity,mLoginManager);


        mLoginPresenter.login("yytian","123");

        verify(mLoginManager).login(eq("yytian"),eq("123"),any(LoginManager.LoginCallBack.class));

    }

    @After
    public void tearDown() throws Exception {

    }
}