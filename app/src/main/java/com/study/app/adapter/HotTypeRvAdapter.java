package com.study.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.app.R;
import com.study.app.holder.RecommendViewHolder;

import java.util.ArrayList;

/**
 * Created by 芮靖林
 * on 2017/1/16 09:07.
 */

public class HotTypeRvAdapter extends RecyclerView.Adapter<RecommendViewHolder> {
    private ArrayList<String> list;
    private Context context;

    public HotTypeRvAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        RecommendViewHolder recommendViewHolder = new RecommendViewHolder(view);
        return recommendViewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        holder.recycler_item_name.setText("------" + list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
