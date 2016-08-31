package com.example.yytian.simplemocktest.retrofit;

/**
 * author： yytian
 * time：   2016/8/31 20:31
 * des：
 */
public class UpdateInfoData {

    /**
     * cur_ver : 19440
     * size : 5736051
     * download_url : http://static.17168.com/app/self/qmyx_app_alpha1_v1.0.0.19440.apk
     * ver_name : v1.0.0.19440
     * ver_info : null
     * need_update : 0
     * update_time : 1472633210
     */

    public int cur_ver;
    public int size;
    public String download_url;
    public String ver_name;
    public Object ver_info;
    public int need_update;
    public int update_time;

    @Override
    public String toString() {
        return "UpdateInfoData{" +
                "cur_ver=" + cur_ver +
                ", size=" + size +
                ", download_url='" + download_url + '\'' +
                ", ver_name='" + ver_name + '\'' +
                ", ver_info=" + ver_info +
                ", need_update=" + need_update +
                ", update_time=" + update_time +
                '}';
    }
}
