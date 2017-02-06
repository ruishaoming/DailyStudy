package com.study.app.bean;

/**
 * author by LiKe on 2017/1/17.
 */

public class TopicParticularsBean {

    /**
     * code : 200
     * data : {"is_commend":"0","isjoin":0,"n_big_img":"http://img.dianfu.net/img/20160628/b5cd189ae8a43c86d10f9523e778301f.jpg","n_brief":"月球探险点这里！好礼相送！","n_icon":"1","n_order":"1","n_post_count":"184","n_praise_count":"355","n_replay_count":"300","n_small_img":"http://img.dianfu.net/img/20160628/07ba9393f52ef5bfe3541b84bdb80f49.jpg","n_status":"1","n_title":"学霸挑战","n_user_count":"422","nid":"6"}
     * msg : 请求成功
     */

    public int code;
    public DataBean data;
    public String msg;

    public static class DataBean {
        /**
         * is_commend : 0
         * isjoin : 0
         * n_big_img : http://img.dianfu.net/img/20160628/b5cd189ae8a43c86d10f9523e778301f.jpg
         * n_brief : 月球探险点这里！好礼相送！
         * n_icon : 1
         * n_order : 1
         * n_post_count : 184
         * n_praise_count : 355
         * n_replay_count : 300
         * n_small_img : http://img.dianfu.net/img/20160628/07ba9393f52ef5bfe3541b84bdb80f49.jpg
         * n_status : 1
         * n_title : 学霸挑战
         * n_user_count : 422
         * nid : 6
         */

        public String is_commend;
        public int isjoin;
        public String n_big_img;
        public String n_brief;
        public String n_icon;
        public String n_order;
        public String n_post_count;
        public String n_praise_count;
        public String n_replay_count;
        public String n_small_img;
        public String n_status;
        public String n_title;
        public String n_user_count;
        public String nid;
    }
}
