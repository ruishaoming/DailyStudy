package com.study.app.utils;


import java.net.URL;

public class URLUtils {

    //基础的ＵＲＬ
    public static final String BASE_URL = "http://www.meirixue.com/";

    //圈子----话题/get请求
    public static final String TOPIC_URL= "http://www.meirixue.com/api.php?c=circle&a=getCircleNamesIndexV2";

    /**
     * 圈子----热门/post请求
     * 请求参数 tid=79 & page=1
     */
    public static final String HOT_URL = "/api.php?c=circle&a=getCirclePostListByTid";

    //圈子----热门/Title/get请求
    public static final String HOT_TITLE_URL = "http://www.meirixue.com/api.php?c=circle&a=getRecommendTag";

}
