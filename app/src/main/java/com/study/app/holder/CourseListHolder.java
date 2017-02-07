package com.study.app.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.app.R;

/**
 * Created by 韩永光
 * on 2017/2/6 08:54.
 */
public class CourseListHolder extends RecyclerView.ViewHolder {

    public ImageView course_list_item_iv_main;
    public TextView course_list_item_tv2;
    public TextView course_list_item_tv3;
    public TextView course_list_item_tv4;
    public TextView course_list_item_tv5;
    public final LinearLayout item_root;

    public CourseListHolder(View itemView) {
        super(itemView);
        course_list_item_iv_main = (ImageView) itemView.findViewById(R.id.course_list_item_iv_main);
        course_list_item_tv2 = (TextView) itemView.findViewById(R.id.course_list_item_tv2);
        course_list_item_tv3 = (TextView) itemView.findViewById(R.id.course_list_item_tv3);
        course_list_item_tv4 = (TextView) itemView.findViewById(R.id.course_list_item_tv4);
        course_list_item_tv5 = (TextView) itemView.findViewById(R.id.course_list_item_tv5);
        item_root = (LinearLayout) itemView.findViewById(R.id.course_list_item_root);

    }
}
