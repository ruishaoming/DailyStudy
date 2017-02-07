package com.study.app.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.study.app.R;
import com.study.app.adapter.ElvAdapter;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.bean.SortBean;
import com.study.app.designs.TitleBuilder;
import com.study.app.interfaces.ICallback;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.NetUtils;
import com.study.app.utils.URLUtils;
import com.study.app.views.ShowingPage;

/**
 * 课程分类
 * Created by 芮靖林
 * on 2017/1/11 10:51.
 */

public class CourseFragment extends BaseFragment implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {

    private boolean CourseFragment_isOnlineAndHasNet = true;
    private ExpandableListView elv;
    private SortBean[] sortBean;
    private ElvAdapter elvadapter;
    private boolean tag=true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (NetUtils.isNoNet()) {
            CourseFragment_isOnlineAndHasNet = false;
        }
    }


    @Override
    protected View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_course);
        //初始化控件
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //重新加载的监听
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                CourseFragment_isOnlineAndHasNet = true;
                getData();
            }
        });

        if (CourseFragment_isOnlineAndHasNet) {
            getData();
        }else {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }

    /**
     * 创建本页面的Title
     *
     * @param showingPage
     */
    @Override
    protected void createTitleView(ShowingPage showingPage) {
        new TitleBuilder(showingPage).setTitleBackGroundColor(Color.RED).setMiddleText("全部分类", 30).setRightImageRes(R.mipmap.glass).setMostRightImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).build();
    }

    /**
     * 是否显示Title
     *
     * @return
     */
    @Override
    protected boolean isNeedTitle() {
        return true;
    }


    private void getData() {

        BaseData b = new BaseData();
        b.getData(URLUtils.BASE_URL, URLUtils.COURSELIST_RUL, BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                sortBean = new Gson().fromJson(responseInfo, SortBean[].class);
                elvadapter = new ElvAdapter(getContext(), sortBean);
                elv.setAdapter(elvadapter);
                //设置首次进入程序  二级列表  子条目的第一个条目为展开
                if (tag) {
                    elv.expandGroup(0);
                    tag = false;
                }
                //打开点击的自条目 关闭已经展开的其他条目
                onGroupExpandListener();
                //group点击事件
                elv.setOnGroupClickListener(CourseFragment.this);
                //children点击事件
                elv.setOnChildClickListener(CourseFragment.this);
                //设置成功的视图
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                //设置失败的视图
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    private void onGroupExpandListener() {
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                // elv是列表实例，通过判断它的状态，关闭已经展开的。
                for (int i = 0; i < 6; i++) {
                    if (groupPosition != i && elv.isGroupExpanded(i)) {
                        elv.collapseGroup(i);
                    }
                }
            }
        });
    }

    private void initView(View view) {
        elv = (ExpandableListView) view.findViewById(R.id.elv);
    }

    //一级列表点击事件
    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

        /** Group字体 颜色设置
         *  bean中的boolear值 如果点击条目==for循环的i值 设定boolear值为true 在适配器的boolear为true
         *  同时刷新适配器
         */
        for (int i = 0; i < sortBean.length; i++) {
            if (i == groupPosition) {
                sortBean[i].setClose(!sortBean[i].isClose());
            } else {
                sortBean[i].setClose(false);
            }
        }

        //二级列表条目点击隐藏设置
        //hideElv(groupPosition);

        //刷新适配器
        elvadapter.notifyDataSetChanged();
        //消费事件
        return false;
    }

    //二级列表点击事件
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getActivity(),"~~"+childPosition,Toast.LENGTH_SHORT).show();
        return true;
    }
    //此方法有BUG

//    private void hideElv(int groupPosition) {
//        if (mCurrentGroupPosition == -1) {
//            //等于-1 --> 没有任何组打开
//
//            //展开组
//            elv.expandGroup(groupPosition);
//            //设置第一个位置是那个组
//            elv.setSelectedGroup(groupPosition);
//            //重新对记录进行赋值
//            mCurrentGroupPosition = groupPosition;
//
//        } else {
//            //不等于-1 --> 代表以前有组打开
//
//            if (groupPosition == mCurrentGroupPosition) {
//                //如果当前点击的是以前打开过的组,那么就关闭它
//                elv.collapseGroup(groupPosition);
//
//                //记录回到最初
//                mCurrentGroupPosition = -1;
//            } else {
//                //如果当前点击的不是以前打开过的组,而是其他组
//
//                //关闭以前的组
//                elv.collapseGroup(mCurrentGroupPosition);
//                //展开当前点击的组
//                elv.expandGroup(groupPosition);
//                //设置第一个位置是那个组
//                elv.setSelectedGroup(groupPosition);
//
//                //重新对记录进行赋值
//                mCurrentGroupPosition = groupPosition;
//            }
//
//        }
//    }
}
