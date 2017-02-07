package com.study.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.study.app.R;
import com.study.app.bean.CourseList;
import com.study.app.holder.CourseListHolder;
import com.study.app.utils.CommonUtils;

import java.util.List;

/**
 * Created by 韩永光
 * on 2017/2/6 08:52.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<CourseListHolder> {
    private final Context context;
    private final List<CourseList.DatalistBean> datalist;
    private final LinearLayout.LayoutParams params;

    public RecycleViewAdapter(Context context, List<CourseList.DatalistBean> datalist) {
        this.context = context;
        this.datalist = datalist;
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public CourseListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = CommonUtils.inflate(R.layout.activity_main2);
        CourseListHolder courseListHolder = new CourseListHolder(view);
        return courseListHolder;
    }

    @Override
    public void onBindViewHolder(CourseListHolder holder, int position) {
        holder.item_root.setLayoutParams(params);
        Glide.with(context).load(datalist.get(position).getCourse_pic()).into(holder.course_list_item_iv_main);
        holder.course_list_item_tv2.setText(datalist.get(position).getCourse_name());
        holder.course_list_item_tv3.setText(datalist.get(position).getSchool_name());
        holder.course_list_item_tv5.setText(datalist.get(position).getCourse_paycount());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
