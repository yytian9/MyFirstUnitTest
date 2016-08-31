package com.example.yytian.simplemocktest.retrofit;

import com.google.gson.JsonObject;

/**
 * author： yytian
 * time：   2016/8/31 16:41
 * des：
 */
public class BaseEntity {

    /**
     * ret : 1
     * cmd : 81000
     * data : {}
     * server_time : 1472632462
     * sign : Nu0WRCTIB/krLCJHGMtf7A==
     */

    public int ret;
    public int cmd;
    public JsonObject data;
    public int server_time;
    public String sign;
}
