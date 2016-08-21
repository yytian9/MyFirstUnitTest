package com.example.yytian.myfirstunittest.login;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Matchers.*;

/**
 * 创建者：     yytian
 * 创建时间：   16-8-21 下午3:31
 * 描述：
 */
@RunWith(RobolectricTestRunner.class)
public class LoginPresenterTest {
    @Mock
    private LoginManager mLoginManager;

    @Mock
    private MainActivity mActivity;

    @InjectMocks
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

        mLoginPresenter.login("yytian","123");
        Mockito.verify(mLoginManager).login("yytian","123",any(LoginCallBack.class));
    }

    @After
    public void tearDown() throws Exception {

    }
}