package com.example.yytian.simplemocktest.data.bean;

/**
 * @创建者 yytian
 * @创建时间 2016/8/28 16:51
 * @描述
 * @更新人 yytian
 * @更新时间 2016/8/28 16:51
 * @更新描述
 */
public class IpInfo {

    /**
     * code : 0
     * data : {"country":"美国","country_id":"US","area":"","area_id":"","region":"","region_id":"","city":"","city_id":"","county":"","county_id":"","isp":"","isp_id":"","ip":"21.22.11.33"}
     */

    public int code;
    /**
     * country : 美国
     * country_id : US
     * area :
     * area_id :
     * region :
     * region_id :
     * city :
     * city_id :
     * county :
     * county_id :
     * isp :
     * isp_id :
     * ip : 21.22.11.33
     */

    public DataEntity data;

    public static class DataEntity {
        public String country;
        public String country_id;
        public String area;
        public String area_id;
        public String region;
        public String region_id;
        public String city;
        public String city_id;
        public String county;
        public String county_id;
        public String isp;
        public String isp_id;
        public String ip;
    }
}
