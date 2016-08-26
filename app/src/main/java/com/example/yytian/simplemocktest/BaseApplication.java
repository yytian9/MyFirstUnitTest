package com.example.yytian.simplemocktest;

import android.app.Application;
import android.os.Handler;

/**
 * 创建者：     yytian
 * 创建时间：   16-8-21 下午12:03
 * 描述：
 */
public class BaseApplication extends Application {

    private static BaseApplication mApplication;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;

    public static Handler getmMainThreadHandler() {
        return mMainThreadHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mMainThreadHandler=new Handler();
    }

    public static BaseApplication getApplication() {
        return mApplication;
    }
}
