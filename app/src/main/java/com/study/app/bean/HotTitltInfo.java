package com.study.app.bean;

import java.util.List;

/**
 * Created by 芮靖林
 * on 2017/1/15 20:57.
 */

public class HotTitltInfo {

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String ctime;
        private String hottopic;
        private String ishot;
        private String isrecommend;
        private String name;
        private String recommend_sort;
        private String sort;
        private String status;
        private String tid;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getHottopic() {
            return hottopic;
        }

        public void setHottopic(String hottopic) {
            this.hottopic = hottopic;
        }

        public String getIshot() {
            return ishot;
        }

        public void setIshot(String ishot) {
            this.ishot = ishot;
        }

        public String getIsrecommend() {
            return isrecommend;
        }

        public void setIsrecommend(String isrecommend) {
            this.isrecommend = isrecommend;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRecommend_sort() {
            return recommend_sort;
        }

        public void setRecommend_sort(String recommend_sort) {
            this.recommend_sort = recommend_sort;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }
    }
}
