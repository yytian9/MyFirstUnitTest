package com.example.yytian.simplemocktest.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author： yytian
 * time：   2016/8/31 10:45
 * des：    a promotion for a test of HBRequestApi,delete when finish test
 *
 *          test result：the request method for retrofit can't be wildcard,
 */
public interface HBRequestApi_copy {
    @GET("/")
    <T> Observable<BaseEntity_copy<T>>   httpGetRequest(@Query("cmd") String cmd, @Query("data") String data,
                                          @Query("guid") String guid,Class<T> clazz);
}
