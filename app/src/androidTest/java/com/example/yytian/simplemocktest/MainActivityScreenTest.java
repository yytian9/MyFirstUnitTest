package com.example.yytian.simplemocktest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.yytian.simplemocktest.login.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * 创建者：     yytian
 * 创建时间：   2016/8/24 17:09
 * 描述：
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityScreenTest {

    private static final String USERNAME_TO_BE_TYPED = "yytian";
    private static final String PASSWORD_TO_BE_TYPED = "123";
    private static final String SUCCUSS_RESULT_TO_BE_SHOW = "登录成功";
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.et_user_name)).perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText(PASSWORD_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.tv_login_result)).check(matches(withText(SUCCUSS_RESULT_TO_BE_SHOW)));
    }
}
