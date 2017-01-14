package com.study.app.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.app.R;

/**
 * Created by ${郭艳杰} on 2017/1/13.
 */

public class RecommendViewHolder extends RecyclerView.ViewHolder {

    public final ImageView recycler_item_img;
    public final TextView recycler_item_name;
    public final TextView recycler_item_count;
    public final TextView recycler_item_price;
    public final TextView recycler_item_school_name;
   public final LinearLayout recycler_item;

    public RecommendViewHolder(View itemView) {
        super(itemView);
        recycler_item_img = (ImageView) itemView.findViewById(R.id.recycler_item_img);
        recycler_item_name = (TextView) itemView.findViewById(R.id.recycler_item_name);
        recycler_item_count = (TextView) itemView.findViewById(R.id.recycler_item_count);
        recycler_item_price = (TextView) itemView.findViewById(R.id.recycler_item_price);
        recycler_item_school_name = (TextView) itemView.findViewById(R.id.recycler_item_school_name);
        recycler_item = (LinearLayout) itemView.findViewById(R.id.recycler_item);
    }

}
