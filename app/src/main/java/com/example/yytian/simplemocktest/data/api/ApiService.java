package com.example.yytian.simplemocktest.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @创建者 yytian
 * @创建时间 2016/8/28 16:49
 */
public interface ApiService {
    @GET("service/getIpInfo.php")
    Call<java.lang.String> getIpInfo(@Query("ip") java.lang.String ip);
}
