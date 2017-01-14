package com.study.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.study.app.R;
import com.study.app.bean.HomeBean;
import com.study.app.holder.RecommendViewHolder;
import com.study.app.interfaces.OnItemClickListener;

import java.util.List;

/**
 * Created by ${郭艳杰} on 2017/1/13.
 */

public class StudyAdapter extends RecyclerView.Adapter<RecommendViewHolder> {
    Context context;
    List<HomeBean.DataBean.IndexothersBean> studyList;
    private OnItemClickListener onItemClickListener;

    public StudyAdapter(Context context, List<HomeBean.DataBean.IndexothersBean> studyList) {
        this.context = context;
        this.studyList = studyList;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycler_item, null);
        RecommendViewHolder viewHolder = new RecommendViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, final int position) {
        holder.recycler_item_name.setText(studyList.get(position).course_name);
        holder.recycler_item_count.setText(studyList.get(position).course_paycount+"人在读");
        holder.recycler_item_school_name.setText(studyList.get(position).school_name);
        Glide.with(context).load(studyList.get(position).course_pic).into(holder.recycler_item_img);
        if (studyList.get(position).course_price.equals("0.00")){
            holder.recycler_item_price.setText("免费");
            holder.recycler_item_price.setTextColor(Color.GREEN);
        }else {
            holder.recycler_item_price.setText(studyList.get(position).course_price);
            holder.recycler_item_price.setTextColor(Color.RED);
        }
        holder.recycler_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return studyList.size();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
