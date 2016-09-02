package com.example.yytian.simplemocktest.data.bean;

/**
 * 创建者：     yytian
 * 创建时间：   2016/8/29 16:13
 * 描述：
 */
public class UpdateInfo_copy {

    /**
     * cur_ver : 19150
     * size : 5400429
     * download_url : http://static.17168.com/app/self/qmyx_app_alpha1_v1.0.0.19150.apk
     * ver_name : v1.0.0.19150
     * ver_info : null
     * need_update : 0
     * update_time : 1472227503
     */

    public int cur_ver;
    public int size;
    public String download_url;
    public String ver_name;
    public String ver_info;
    public int need_update;
    public int update_time;

    @Override
    public String toString() {
        return "UpdateInfo_copy{" +
                "cur_ver=" + cur_ver +
                ", size=" + size +
                ", download_url='" + download_url + '\'' +
                ", ver_name='" + ver_name + '\'' +
                ", ver_info='" + ver_info + '\'' +
                ", need_update=" + need_update +
                ", update_time=" + update_time +
                '}';
    }
}
