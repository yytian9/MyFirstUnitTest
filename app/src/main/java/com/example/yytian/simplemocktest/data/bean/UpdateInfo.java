package com.example.yytian.simplemocktest.data.bean;

/**
 * 创建者：     yytian
 * 创建时间：   2016/8/29 16:13
 * 描述：
 */
public class UpdateInfo {

    /**
     * ret : 1
     * cmd : 82101
     * data : {"cur_ver":19150,"size":5400429,"download_url":"http://static.17168.com/app/self/qmyx_app_alpha1_v1.0.0.19150.apk","ver_name":"v1.0.0.19150","ver_info":null,"need_update":0,"update_time":1472227503}
     * server_time : 1472457306
     * sign : 2XKLVOzPrbLuQ4JZTemF4w==
     */

    public int ret;
    public int cmd;
    /**
     * cur_ver : 19150
     * size : 5400429
     * download_url : http://static.17168.com/app/self/qmyx_app_alpha1_v1.0.0.19150.apk
     * ver_name : v1.0.0.19150
     * ver_info : null
     * need_update : 0
     * update_time : 1472227503
     */

    public DataEntity data;
    public int server_time;
    public String sign;

    public static class DataEntity {
        public int cur_ver;
        public int size;
        public String download_url;
        public String ver_name;
        public Object ver_info;
        public int need_update;
        public int update_time;
    }
}
