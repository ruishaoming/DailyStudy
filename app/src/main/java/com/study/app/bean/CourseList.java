package com.study.app.bean;

import java.util.List;

/**
 * Created by 韩永光
 * on 2017/2/6 08:40.
 */
public class CourseList {

    /**
     * datalist : [{"cid":"5662","course_tname":"刘景超","course_name":"实用编发教程（28）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161209/b773239fe5b727ed17607a7aa7e3573e.jpg","course_paycount":"105","school_name":"优学教育"},{"cid":"5615","course_tname":"刘景超","course_name":"实用编发教程（28）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/bf307f6af18e5654078a147d1a10697a.jpg","course_paycount":"25","school_name":"优学教育"},{"cid":"5614","course_tname":"刘景超","course_name":"实用编发教程（27）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/611cffd164771a53a90d30401d0fb042.jpg","course_paycount":"11","school_name":"优学教育"},{"cid":"5613","course_tname":"刘景超","course_name":"实用编发教程（26）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/466ac1a2d98ce45846ff1e984f6900ed.jpg","course_paycount":"23","school_name":"优学教育"},{"cid":"5612","course_tname":"刘景超","course_name":"实用编发教程（25）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/aeef141fc462557e587edae7771d1350.jpg","course_paycount":"11","school_name":"优学教育"},{"cid":"5611","course_tname":"刘景超","course_name":"实用编发教程（24）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/edf0f68598965df684507630c683e8db.jpg","course_paycount":"18","school_name":"优学教育"},{"cid":"5610","course_tname":"刘景超","course_name":"实用编发教程（23）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/37f0dc3803dd27e087380d147221eb96.jpg","course_paycount":"6","school_name":"优学教育"},{"cid":"5609","course_tname":"刘景超","course_name":"实用编发教程（22）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/7c1f46ea5d9e1663f04c50ee1505e776.jpg","course_paycount":"9","school_name":"优学教育"},{"cid":"5608","course_tname":"刘景超","course_name":"实用编发教程（21）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/c0fc7c7a79debc1f5e561471d841548f.jpg","course_paycount":"5","school_name":"优学教育"},{"cid":"5607","course_tname":"刘景超","course_name":"实用编发教程（20）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161128/ac87f3bfedf7d26b7f591f0273b6579d.jpg","course_paycount":"8","school_name":"优学教育"}]
     * count : 141
     * limit : 10
     * curpage : 1
     */

    private int count;
    private int limit;
    private String curpage;
    private List<DatalistBean> datalist;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCurpage() {
        return curpage;
    }

    public void setCurpage(String curpage) {
        this.curpage = curpage;
    }

    public List<DatalistBean> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DatalistBean> datalist) {
        this.datalist = datalist;
    }

    public static class DatalistBean {
        /**
         * cid : 5662
         * course_tname : 刘景超
         * course_name : 实用编发教程（28）
         * course_price : 0.00
         * course_pic : http://img.dianfu.net/img/20161209/b773239fe5b727ed17607a7aa7e3573e.jpg
         * course_paycount : 105
         * school_name : 优学教育
         */

        private String cid;
        private String course_tname;
        private String course_name;
        private String course_price;
        private String course_pic;
        private String course_paycount;
        private String school_name;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCourse_tname() {
            return course_tname;
        }

        public void setCourse_tname(String course_tname) {
            this.course_tname = course_tname;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_price() {
            return course_price;
        }

        public void setCourse_price(String course_price) {
            this.course_price = course_price;
        }

        public String getCourse_pic() {
            return course_pic;
        }

        public void setCourse_pic(String course_pic) {
            this.course_pic = course_pic;
        }

        public String getCourse_paycount() {
            return course_paycount;
        }

        public void setCourse_paycount(String course_paycount) {
            this.course_paycount = course_paycount;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }
    }
}
