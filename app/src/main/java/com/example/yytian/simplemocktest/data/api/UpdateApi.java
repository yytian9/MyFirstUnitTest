package com.example.yytian.simplemocktest.data.api;

import com.example.yytian.simplemocktest.data.bean.UpdateInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建者：     yytian
 * 创建时间：   2016/8/29 16:14
 * 描述：
 */
public interface UpdateApi {
    @GET("/")
    Observable<UpdateInfo> getUpdateInfo(@Query("cmd") String cmd, @Query("guid") String guid);
}
