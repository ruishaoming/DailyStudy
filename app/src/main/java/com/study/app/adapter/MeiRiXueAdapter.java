package com.study.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.study.app.R;
import com.study.app.bean.MeiRiXueBean;
import com.study.app.holder.RecommendViewHolder;
import com.study.app.interfaces.OnItemClickListener;

import java.util.List;

/**
 * Created by ${郭艳杰} on 2017/1/17.
 */

public class MeiRiXueAdapter extends RecyclerView.Adapter<RecommendViewHolder> {
    Context context;
    List<MeiRiXueBean.DataListBean.ListBean> dataList;
    private OnItemClickListener onItemClicListener;

    public MeiRiXueAdapter(Context context, List<MeiRiXueBean.DataListBean.ListBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycler_item, null);
        RecommendViewHolder viewHolder = new RecommendViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, final int position) {
        holder.recycler_item_name.setText(dataList.get(position).course_name);
        holder.recycler_item_school_name.setText(dataList.get(position).school_name);
        holder.recycler_item_count.setText(dataList.get(position).course_paycount+"人在读");
        Glide.with(context).load(dataList.get(position).course_pic).into(holder.recycler_item_img);
        holder.recycler_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (dataList.get(position).course_price.equals("0.00")){
            holder.recycler_item_price.setText("免费");
            holder.recycler_item_price.setTextColor(Color.GREEN);
        }else {
            holder.recycler_item_price.setText(dataList.get(position).course_price);
            holder.recycler_item_price.setTextColor(Color.RED);
        }
        holder.recycler_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClicListener!=null){
                    onItemClicListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClicListener = onItemClickListener;
    }
}
