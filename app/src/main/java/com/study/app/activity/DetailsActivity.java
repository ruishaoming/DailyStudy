package com.study.app.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.study.app.R;
import com.study.app.base.BaseData;
import com.study.app.base.BaseShowingPageActivity;
import com.study.app.bean.CourseInfoBean;
import com.study.app.bean.MeiRiXueBean;
import com.study.app.designs.CourseInfoFragmentFactory;
import com.study.app.designs.TitleBuilder;
import com.study.app.interfaces.ICallback;
import com.study.app.views.ShowingPage;

import java.util.HashMap;
import java.util.List;

/**
 * 芮靖林：
 * 详情Activity
 */
public class DetailsActivity extends BaseShowingPageActivity {

    private String responseInfo;
    private TextView textView;
    private ImageView course_info_img;
    private TextView course_info_name;
    private TextView course_info_price;
    private TabLayout course_info_tabLayout;
    private ViewPager course_info_viewPager;
    private TextView course_sum;
    private TextView course_comment;
    private TextView course_read_count;
    String[] title = {"详情", "目录", "评论（0）"};
    HashMap<String, String> map = new HashMap<>();
    private String url;
    private List<MeiRiXueBean.DataListBean.ListBean> list;
    private MeiRiXueBean viewPagerBean;
    private CourseInfoBean courseInfoBean;
    private CourseInfoBean.DataBean data;

    @Override
    protected void onLoad() {
        BaseData baseData = new BaseData();
        map.put("courseid", url);
        baseData.postData(false, false, "http://www.meirixue.com", "/api.php?c=course&a=getCourseInfo", BaseData.SHORT_TIME, map, new ICallback() {

            @Override
            public void onResponse(String responseInfo) {
                Gson gson = new Gson();
                courseInfoBean = gson.fromJson(responseInfo, CourseInfoBean.class);
                data = courseInfoBean.data;
                DetailsActivity.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SetData();
                        initViewPager();
                    }
                });
            }

            @Override
            public void onFailure(String errorInfo) {
                DetailsActivity.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });

    }

    @Override
    protected void createTitleView() {
        new TitleBuilder(showingPage).setTitleBackGroundColor(getResources().getColor(R.color.main_bottom_tv_check)).setLeftImageRes(R.mipmap.btn_back).setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setMiddleText("课程详情", 20).setRightImageRes(R.mipmap.associated_course).setRightImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).setMostRightImageRes(R.mipmap.share).setMostRightImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected View createSuccessView() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_details, null);
        url = getIntent().getStringExtra("url");
        initView(view);

        return view;
    }

    private void SetData() {
        Glide.with(this).load(data.course_pic).into(course_info_img);
        course_info_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        course_info_name.setText(data.course_name);
        course_info_price.setText(data.course_price);
        course_sum.setText("总课时:  " + data.course_hour);
        course_comment.setText("评分:  " + data.goodrate);
        course_read_count.setText(data.course_paycount + "人学");
    }

    //找控件
    private void initView(View view) {
        course_info_img = (ImageView) view.findViewById(R.id.course_info_img);
        course_info_name = (TextView) view.findViewById(R.id.course_info_name);
        course_info_price = (TextView) view.findViewById(R.id.course_info_price);
        course_info_tabLayout = (TabLayout) view.findViewById(R.id.course_info_tabLayout);
        course_info_viewPager = (ViewPager) view.findViewById(R.id.course_info_viewPager);
        course_sum = (TextView) view.findViewById(R.id.course_sum);
        course_comment = (TextView) view.findViewById(R.id.course_comment);
        course_read_count = (TextView) view.findViewById(R.id.course_read_count);


    }

    private void initViewPager() {
        LinearLayout linearLayout = (LinearLayout) course_info_tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(50);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_divider_vertical));

        course_info_tabLayout.addTab(course_info_tabLayout.newTab().setText(title[0]), false);
        course_info_tabLayout.addTab(course_info_tabLayout.newTab().setText(title[1]), true);
        course_info_tabLayout.addTab(course_info_tabLayout.newTab().setText(title[2]), false);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return CourseInfoFragmentFactory.getFragment(position);
            }

            @Override
            public int getCount() {
                return 3;
            }

//            @Override
//            public CharSequence getPageTitle(int position) {
//                return title[position];
//            }
        };

//        course_info_tabLayout.setTabsFromPagerAdapter(fragmentPagerAdapter);

//        course_info_tabLayout.setupWithViewPager(course_info_viewPager);

        course_info_viewPager.setAdapter(fragmentPagerAdapter);
        course_info_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                course_info_tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        course_info_tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                course_info_viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}
