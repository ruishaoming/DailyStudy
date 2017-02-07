package com.study.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.study.app.R;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.bean.HotTitltInfo;
import com.study.app.interfaces.ICallback;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.LogUtils;
import com.study.app.utils.NetUtils;
import com.study.app.utils.URLUtils;
import com.study.app.views.ShowingPage;

import java.util.HashMap;

/**
 * Created by 芮靖林
 * on 2017/1/14 13:34.
 */

public class HotFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mVp;
    private boolean HotFragment_isOnlineAndHasNet = true;

    //进行网路判断
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (NetUtils.isNoNet()) {
            HotFragment_isOnlineAndHasNet = false;
        }
    }

    @Override
    protected void createTitleView(ShowingPage showingPage) {

    }

    @Override
    protected boolean isNeedTitle() {
        return false;
    }

    @Override
    protected View createSuccessView() {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hot, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mTabLayout = (TabLayout) rootView.findViewById(R.id.hot_tablayout);
        mVp = (ViewPager) rootView.findViewById(R.id.hot_vp);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //处理数据请求及网络
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                HotFragment_isOnlineAndHasNet = true;
                getTtile();
            }
        });
        if (HotFragment_isOnlineAndHasNet) {
            getTtile();
        }else {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }

    //获取Title数据
    private void getTtile() {
        BaseData baseData = new BaseData();
        baseData.getData(URLUtils.BASE_URL, URLUtils.HOT_TITLE_URL, BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                HotTitltInfo hotTitltInfo = new Gson().fromJson(responseInfo, HotTitltInfo.class);
                initTitle(hotTitltInfo);
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    //初始化Title
    private void initTitle(final HotTitltInfo hotTitltInfo) {
        for (int i = 0; i < hotTitltInfo.getData().size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(hotTitltInfo.getData().get(i).getName()));
        }
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return HotTypeFragment.getTitleTid(hotTitltInfo.getData().get(position).getTid());
            }

            @Override
            public int getCount() {
                return hotTitltInfo.getData().size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return hotTitltInfo.getData().get(position).getName();
            }
        };

        //设置与ViewPager的关联

        mVp.setAdapter(fragmentPagerAdapter);

        mTabLayout.setTabsFromPagerAdapter(fragmentPagerAdapter);

        mTabLayout.setupWithViewPager(mVp);
    }

}
