package com.study.app.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.R;
import com.study.app.application.MyApplication;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.designs.QuanFragmentFactory;
import com.study.app.designs.TitleBuilder;
import com.study.app.interfaces.ICallback;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.NetUtils;
import com.study.app.views.ShowingPage;

/**
 * Created by 芮靖林
 * on 2017/1/11 10:52.
 */

public class QuanFragment extends BaseFragment implements View.OnClickListener {

    private String responseInfo;
    private TextView textView;
    private TabLayout mTabLayout;
    //pagerslidingtabstrip
    private String[] titleArray = {"话题", "热门", "关注"};
    private ViewPager mViewPager;

    @Override
    protected View createSuccessView() {
        View homeView = CommonUtils.inflate(R.layout.fragment_quan);
        mViewPager = (ViewPager) homeView.findViewById(R.id.quan_vp);
        return homeView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPageAdataer();
        showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    private void initPageAdataer() {

        //给TabLayout设置中间的线条
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(20);

        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.tablayout_divider_vertical));
        //设置ViewPager的适配器
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return QuanFragmentFactory.getFragment(position);
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleArray[position];
            }
        };

        mViewPager.setAdapter(fragmentPagerAdapter);

        mTabLayout.setTabsFromPagerAdapter(fragmentPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }

    //创建Title
    @Override
    protected void createTitleView(ShowingPage showingPage) {
        View titleView = LayoutInflater.from(getActivity()).inflate(R.layout.title_quan, null);
        mTabLayout = (TabLayout) titleView.findViewById(R.id.quan_tablayout);
        titleView.findViewById(R.id.quan_title_search).setOnClickListener(this);
        titleView.findViewById(R.id.quan_title_add_friend).setOnClickListener(this);

        //设置TabLayout的内容
        for (int i = 0; i < titleArray.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titleArray[i]));
        }

        new TitleBuilder(showingPage).createSelfTitleView(titleView).build();
    }

    @Override
    protected boolean isNeedTitle() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //左上角添加好友按钮
            case R.id.quan_title_add_friend:
                break;
            //有上角搜索
            case R.id.quan_title_search:
                break;
        }
    }
}
