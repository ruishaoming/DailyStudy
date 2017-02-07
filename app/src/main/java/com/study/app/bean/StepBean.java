package com.study.app.bean;

import java.util.List;

/**
 * Created by ${郭艳杰} on 2017/2/5.
 */

public class StepBean {

    /**
     * code : 200
     * data : [{"id":"5040","step_name":"小白淘屋编织教程之围巾1","step_course_id":"5252","step_order":"1","nodes":[{"seid":"25740","sections_name":"起针与收尾","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"1","vtime":441640},{"seid":"25741","sections_name":"机器边起针","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"2","vtime":504680},{"seid":"25742","sections_name":"起针针数计算","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"3","vtime":344120},{"seid":"26124","sections_name":"上下针","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"4","vtime":265380},{"seid":"25743","sections_name":"方块针","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"5","vtime":261120}]}]
     * msg :
     * course_name : 小白淘屋编织教程之围巾1
     */

    public int code;
    public String msg;
    public String course_name;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 5040
         * step_name : 小白淘屋编织教程之围巾1
         * step_course_id : 5252
         * step_order : 1
         * nodes : [{"seid":"25740","sections_name":"起针与收尾","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"1","vtime":441640},{"seid":"25741","sections_name":"机器边起针","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"2","vtime":504680},{"seid":"25742","sections_name":"起针针数计算","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"3","vtime":344120},{"seid":"26124","sections_name":"上下针","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"4","vtime":265380},{"seid":"25743","sections_name":"方块针","sections_chid":"5040","sections_des":"","sections_isfree":"1","sections_sort":"5","vtime":261120}]
         */

        public String id;
        public String step_name;
        public String step_course_id;
        public String step_order;
        public List<NodesBean> nodes;

        public static class NodesBean {
            /**
             * seid : 25740
             * sections_name : 起针与收尾
             * sections_chid : 5040
             * sections_des :
             * sections_isfree : 1
             * sections_sort : 1
             * vtime : 441640
             */

            public String seid;
            public String sections_name;
            public String sections_chid;
            public String sections_des;
            public String sections_isfree;
            public String sections_sort;
            public int vtime;
        }
    }
}
