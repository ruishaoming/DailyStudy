package com.study.app.utils;


public class URLUtils {

    //基础的ＵＲＬ
    public static final String BASE_URL = "http://www.meirixue.com/";

    //圈子----话题/get请求
    public static final String TOPIC_URL = "http://www.meirixue.com/api.php?c=circle&a=getCircleNamesIndexV2";

    /**
     * 圈子----热门/post请求
     * 请求参数 tid=79 & page=1
     */
    public static final String HOT_URL = "/api.php?c=circle&a=getCirclePostListByTid";

    //圈子----热门/Title
    public static final String HOT_TITLE_URL = "http://www.meirixue.com/api.php?c=circle&a=getRecommendTag";

    /*

    首页/post请求

    请求参数 a=indexv9   c=index

    */
    public static final String Home_URL = "http://www.meirixue.com/api.php";
    /*
    * 首页轮播图点击详情/post请求
    * 请求参数 aid=17
    * */
    public static final String ViewPager_URL = "http://www.meirixue.com/api.php?c=activity&a=getActivityBak";
    // http://www.meirixue.com/api.php?c=course&a=getCourseInf
    /*
    * post 请求
    * 请求参数 courseid=5221
    * */
    public static final String Course_URL = "http://www.meirixue.com/api.php?c=course&a=getCourseInf";
    //http://www.meirixue.com/api.php?c=course&a=getCourseCommenttV2
    //http://www.meirixue.com/api.php?c=course&a=getCourseStep
    //http://www.meirixue.com/api.php?c=course&a=getCourseDesc
}
