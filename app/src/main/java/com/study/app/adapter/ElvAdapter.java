package com.study.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.R;
import com.study.app.activity.CourseActivity;
import com.study.app.bean.SortBean;
import com.study.app.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by 韩永光
 * on 2017/1/12 15:25.
 */
public class ElvAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final SortBean[] sortBean;
    private ArrayList<Integer> photoList;
    Integer text_colourlist[] = {R.color.c1, R.color.c2, R.color.c3, R.color.c4, R.color.c5, R.color.c6};
    Integer img_colourlist[] = {R.mipmap.heart_s, R.mipmap.coffee_s, R.mipmap.diamond_s, R.mipmap.heart_s, R.mipmap.hat_s, R.mipmap.language_s};
    private ImageView imgleft_elv;
    private ImageView imgright1_elv;
    private ImageView imgright2_elv;
    private boolean ischecked;
    private GridView gv_elv;


    public ElvAdapter(Context context, SortBean[] sortBean) {
        this.context = context;
        this.sortBean = sortBean;

        for (int i = 0; i < sortBean.length; i++) {
            if (i > 0) {
                SortBean.NodesBean nodeBean = new SortBean.NodesBean();
                nodeBean.setCategory_name("全部");
                sortBean[i].getNodes().add(0, nodeBean);
            }
        }
        photoList = new ArrayList<>();
        photoList.add(R.mipmap.heart);
        photoList.add(R.mipmap.coffee);
        photoList.add(R.mipmap.diamond);
        photoList.add(R.mipmap.portfolio);
        photoList.add(R.mipmap.hat);
        photoList.add(R.mipmap.language);
    }


    //第一列表的个数
    @Override
    public int getGroupCount() {
        return sortBean.length;
    }

    //第二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    //获取给定的一级列表的数据
    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    //获取给定的二级列表的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    //获取一级列表给定的Id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取二级列表给定的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //返回一级列表的条目布局
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = CommonUtils.inflate(R.layout.elv_groupitem);
        TextView tv_elv = (TextView) view.findViewById(R.id.tv_elv);
        imgleft_elv = (ImageView) view.findViewById(R.id.imgleft_elv);
        imgright1_elv = (ImageView) view.findViewById(R.id.imgright1_elv);
        imgright2_elv = (ImageView) view.findViewById(R.id.imgright2_elv);
        tv_elv.setText(sortBean[groupPosition].getCname());
        //设置条目点击不变色
        view.setBackgroundColor(Color.WHITE);

        imgleft_elv.setImageResource(photoList.get(groupPosition));
        //条目被点击
        if (sortBean[groupPosition].isClose()) {
            tv_elv.setTextColor(context.getResources().getColor(text_colourlist[groupPosition]));
            imgright2_elv.setImageResource(img_colourlist[groupPosition]);
            imgright1_elv.setVisibility(View.GONE);
            imgright2_elv.setVisibility(View.VISIBLE);
        } else {
            tv_elv.setTextColor(Color.BLACK);
            imgright1_elv.setVisibility(View.VISIBLE);
            imgright2_elv.setVisibility(View.GONE);
        }
        return view;
    }

    //返回二级列表的条目布局
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final View view = CommonUtils.inflate(R.layout.elv_childitem);
        gv_elv = (GridView) view.findViewById(R.id.gv_elv);
        gv_elv.setCacheColorHint(0);

        gv_elv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return sortBean[groupPosition].getNodes().size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view1 = CommonUtils.inflate(R.layout.elvchild_gridview_item);
                TextView tv_elvchild = (TextView) view1.findViewById(R.id.tv_elvchild);
                tv_elvchild.setText(sortBean[groupPosition].getNodes().get(position).getCategory_name());

                return view1;
            }

        });
        //二级列表子条目点击事件
        gv_elv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1 = sortBean[groupPosition].getNodes().get(position).getId();
                String category_name = sortBean[groupPosition].getNodes().get(position).getCategory_name();
                Intent intent=new Intent(context,CourseActivity.class);
                intent.putExtra("id",id1);
                intent.putExtra("name",category_name);
                context.startActivity(intent);

                Toast.makeText(context,"~~~~~"+id1,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //控制二级列表条目是否可以点击
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;

    }


}
