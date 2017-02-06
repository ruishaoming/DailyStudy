package com.study.app.utils;


public class URLUtils {

    //基础的ＵＲＬ
    public static final String BASE_URL = "http://www.meirixue.com/";
    //分类页面的数据
    public static final String COURSELIST_RUL = "http://www.meirixue.com/api.php?c=category&a=getall";
    //圈子----话题/get请求
    public static final String TOPIC_URL = "http://www.meirixue.com/api.php?c=circle&a=getCircleNamesIndexV2";

    /**
     * 圈子----热门/post请求
     * 请求参数 tid=79 & page=1
     */
    public static final String HOT_URL = "api.php?c=circle&a=getCirclePostListByTid";

    //圈子----热门/Title/get请求
    public static final String HOT_TITLE_URL = "http://www.meirixue.com/api.php?c=circle&a=getRecommendTag";

    //登录接口/post
    /**
     * userName=13520183019&password=123&dosubmit=1
     */
    public static final String LOGIN_URL = "api.php?c=login&a=index";

    /**
     * post
     * 圈子话题条目点击
     */
    public static final String TOPIC_ITEM = "api.php?c=circle&a=getCircleNameInfo";
    /**
     * 话题**最新最热
     */
    public static final String TOPIC_HOT_NEWS = "api.php?c=circle&a=getCirclePostList";

}
