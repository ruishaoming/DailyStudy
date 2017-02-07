package com.study.app.utils;


public class URLUtils {

    //基础的ＵＲＬ
    public static final String BASE_URL = "http://www.meirixue.com/";
    //分类页面的数据
    public static final String COURSELIST_RUL = "http://www.meirixue.com/api.php?c=category&a=getall";
    //分类页面的post数据
    public static final String COURSELIST_RUL_POST =" /api.php?c=list&a=index";
    //圈子----话题/get请求
    public static final String TOPIC_URL= "http://www.meirixue.com/api.php?c=circle&a=getCircleNamesIndexV2";
    // 三级分类的数据
    public static final String THREECOURSE_URL= "http://www.meirixue.com/api.php?c=category&a=getTree";

    /**
     * 圈子----热门/post请求
     * 请求参数 tid=79 & page=1
     */
    public static final String HOT_URL = "/api.php?c=circle&a=getCirclePostListByTid";

    //圈子----热门/Title
    public static final String HOT_TITLE_URL = "http://www.meirixue.com/api.php?c=circle&a=getRecommendTag";

}
