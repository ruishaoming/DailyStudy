package com.study.app.bean;

import java.util.List;

/**
 * Created by 芮靖林
 * on 2017/1/14 17:03.
 */

public class TopicInfo {

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<BannerBean> banner;
        private List<CircleBean> circle;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CircleBean> getCircle() {
            return circle;
        }

        public void setCircle(List<CircleBean> circle) {
            this.circle = circle;
        }

        public static class BannerBean {

            private String id;
            private String img;
            private String order;
            private String status;
            private String title;
            private String type;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class CircleBean {

            private String is_commend;
            private String n_big_img;
            private String n_brief;
            private String n_icon;
            private String n_order;
            private String n_post_count;
            private String n_praise_count;
            private String n_replay_count;
            private String n_small_img;
            private String n_status;
            private String n_title;
            private String n_user_count;
            private String nid;

            public String getIs_commend() {
                return is_commend;
            }

            public void setIs_commend(String is_commend) {
                this.is_commend = is_commend;
            }

            public String getN_big_img() {
                return n_big_img;
            }

            public void setN_big_img(String n_big_img) {
                this.n_big_img = n_big_img;
            }

            public String getN_brief() {
                return n_brief;
            }

            public void setN_brief(String n_brief) {
                this.n_brief = n_brief;
            }

            public String getN_icon() {
                return n_icon;
            }

            public void setN_icon(String n_icon) {
                this.n_icon = n_icon;
            }

            public String getN_order() {
                return n_order;
            }

            public void setN_order(String n_order) {
                this.n_order = n_order;
            }

            public String getN_post_count() {
                return n_post_count;
            }

            public void setN_post_count(String n_post_count) {
                this.n_post_count = n_post_count;
            }

            public String getN_praise_count() {
                return n_praise_count;
            }

            public void setN_praise_count(String n_praise_count) {
                this.n_praise_count = n_praise_count;
            }

            public String getN_replay_count() {
                return n_replay_count;
            }

            public void setN_replay_count(String n_replay_count) {
                this.n_replay_count = n_replay_count;
            }

            public String getN_small_img() {
                return n_small_img;
            }

            public void setN_small_img(String n_small_img) {
                this.n_small_img = n_small_img;
            }

            public String getN_status() {
                return n_status;
            }

            public void setN_status(String n_status) {
                this.n_status = n_status;
            }

            public String getN_title() {
                return n_title;
            }

            public void setN_title(String n_title) {
                this.n_title = n_title;
            }

            public String getN_user_count() {
                return n_user_count;
            }

            public void setN_user_count(String n_user_count) {
                this.n_user_count = n_user_count;
            }

            public String getNid() {
                return nid;
            }

            public void setNid(String nid) {
                this.nid = nid;
            }
        }
    }
}
