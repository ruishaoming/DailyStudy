package com.study.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.study.app.R;
import com.study.app.activity.DetailsActivity;
import com.study.app.base.BaseData;
import com.study.app.bean.CourseInfoBean;
import com.study.app.bean.StepBean;
import com.study.app.interfaces.ICallback;
import com.study.app.utils.TimeUtils;
import com.study.app.utils.URLUtils;
import com.study.app.views.MyListView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ${郭艳杰} on 2017/1/16.
 */

public class CatalogFragment extends Fragment {

    private TextView sections_sort;
    private TextView sections_name;
    private TextView tv_time;
    private String cid;
    private StepBean stepBean;
    private TextView step_name;
    private MyListView catelog_lv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catelogitem, null);
        catelog_lv = (MyListView) view.findViewById(R.id.catelog_lv);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
    }


    private void getData() {
        DetailsActivity detailsActivity = (DetailsActivity) getActivity();
        CourseInfoBean courseInfoBean = detailsActivity.courseInfoBean;
        if (courseInfoBean!=null){
            cid = courseInfoBean.data.cid;
            HashMap<String,String> map = new HashMap<>();
            map.put("courseid", cid);
            BaseData baseData = new BaseData();

            baseData.postData(false, false, URLUtils.CATELOG_BASEURL, URLUtils.CATELOG_URL, BaseData.SHORT_TIME, map, new ICallback() {

                private List<StepBean.DataBean> data;
                private List<StepBean.DataBean.NodesBean> nodes;

                @Override
                public void onResponse(String responseInfo) {
                    Gson gson = new Gson();
                    stepBean = gson.fromJson(responseInfo, StepBean.class);
                    Log.e("TAG","***************"+stepBean);
                    nodes = stepBean.data.get(0).nodes;
                    data = stepBean.data;

                    catelog_lv.setAdapter(new CommonAdapter<StepBean.DataBean>(getActivity(),R.layout.catelog_lv_items,data) {

                        @Override
                        protected void convert(ViewHolder viewHolder, StepBean.DataBean item, int position) {
                           TextView step_name =  viewHolder.getView(R.id.step_name);
                            step_name.setText("第"+item.step_order+"章:"+stepBean.data.get(position).step_name);
                            MyListView catelog_myListView = viewHolder.getView(R.id.catelog_myListView);

                            catelog_myListView.setAdapter(new CommonAdapter<StepBean.DataBean.NodesBean>(getActivity(),R.layout.catelog_lv_item,nodes) {
                                @Override
                                protected void convert(ViewHolder viewHolder, StepBean.DataBean.NodesBean items, int i) {
                                    TextView sections_name = viewHolder.getView(R.id.sections_name);
                                    TextView sections_sort =  viewHolder.getView(R.id.sections_sort);
                                    TextView tv_time =  viewHolder.getView(R.id.tv_time);
                                    sections_name.setText(items.sections_name);
                                    sections_sort.setText(items.sections_isfree+"-"+items.sections_sort);
                                    tv_time.setText(TimeUtils.formatTime(items.vtime));
                                }
                            });


                        }
                    });


                }



                @Override
                public void onFailure(String errorInfo) {

                }
            });
        }

    }


}
