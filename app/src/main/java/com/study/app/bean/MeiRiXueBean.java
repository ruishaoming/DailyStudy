package com.study.app.bean;

import java.util.List;

/**
 * Created by ${郭艳杰} on 2017/1/16.
 */

public class MeiRiXueBean {


    public int status;
    public String showtype;
    public String desc;
    public String toppic;
    public List<DataListBean> dataList;

    public static class DataListBean {

        public String title;
        public List<ListBean> list;

        public static class ListBean {

            public String cid;
            public String course_name;
            public String course_paycount;
            public String course_price;
            public String course_pic;
            public String sid;
            public String school_name;
        }
    }
}
