package com.example.yytian.simplemocktest.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author： yytian
 * time：   2016/8/31 10:45
 * des：
 */
public interface HBRequestApi {
    @GET("/")
    Observable<BaseEntity> httpGetRequest(@Query("cmd") String cmd, @Query("data") String data,
                                          @Query("guid") String guid);
}
