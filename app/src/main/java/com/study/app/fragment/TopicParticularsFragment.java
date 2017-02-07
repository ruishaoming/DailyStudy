package com.study.app.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.study.app.R;
import com.study.app.activity.TopicActivity;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.bean.HotTitlesContentBean;
import com.study.app.interfaces.ICallback;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.URLUtils;
import com.study.app.views.MyHeader2;
import com.study.app.views.ShowingPage;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Created by 芮靖林
 * on 2017/2/4 16:38.
 */

public class TopicParticularsFragment extends BaseFragment implements SpringView.OnFreshListener,AppBarLayout.OnOffsetChangedListener {

    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatBtn;
    private int page = 1;
    private ArrayList<HotTitlesContentBean.DataBean> listAll = new ArrayList<>();
    private boolean isRefreshLoad = true;
    private CommonAdapter commonAdapter;

    @Override
    protected void createTitleView(ShowingPage showingPage) {

    }

    @Override
    protected boolean isNeedTitle() {
        return false;
    }

    @Override
    protected View createSuccessView() {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hot_type, null);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {

        mSpringView = (SpringView) rootView.findViewById(R.id.hot_type_springview);
        mSpringView.setListener(this);
        mSpringView.setHeader(new MyHeader2());
        mSpringView.setFooter(new DefaultFooter(getActivity()));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.hot_type_rec);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFloatBtn = (FloatingActionButton) rootView.findViewById(R.id.hot_type_FloatingActionButton);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置MyTopicDetail中appBarLayout的监听事件
        if(TopicActivity.appBarLayout!=null)
            TopicActivity.appBarLayout.addOnOffsetChangedListener(this);
        getData();
    }

    private void getData() {
        //获取参数信息
        String title = this.getArguments().getString("title");
        String nid = this.getArguments().getString("nid");
        //设置参数
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("nid", nid);
        if (title.equals("最新")) {
            hashMap.put("order", 1 + "");
        } else {
            hashMap.put("order", 2 + "");
        }
        hashMap.put("page", page + "");
        new BaseData().postData(false, false, URLUtils.BASE_URL, URLUtils.TOPIC_HOT_NEWS, BaseData.SHORT_TIME, hashMap, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                Gson gson = new Gson();
                HotTitlesContentBean hotTitlesContentBean = gson.fromJson(responseInfo, HotTitlesContentBean.class);
                //设置recyclerView适配器
                initRecyclerViewDatas(hotTitlesContentBean);
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    /**
     * 设置recyclerView适配器
     *
     * @param hotTitlesContentBean
     */
    private void initRecyclerViewDatas(HotTitlesContentBean hotTitlesContentBean) {
        List<HotTitlesContentBean.DataBean> dataBeanList = hotTitlesContentBean.data;
        if (isRefreshLoad) {
            listAll.clear();
            listAll.addAll(dataBeanList);
        } else {
            listAll.addAll(dataBeanList);
        }
        if (commonAdapter == null) {
            setRecyclerViewAdapter(listAll);
            mRecyclerView.setAdapter(commonAdapter);
        } else {
            commonAdapter.notifyDataSetChanged();
        }

        CommonUtils.setFabAnim(getActivity(),mRecyclerView,mFloatBtn);
    }


    private void setRecyclerViewAdapter(final List<HotTitlesContentBean.DataBean> refreshLoadList) {
        commonAdapter = new CommonAdapter<HotTitlesContentBean.DataBean>(getActivity(), R.layout.hot_type_rv_item, refreshLoadList) {
            @Override
            protected void convert(ViewHolder holder, HotTitlesContentBean.DataBean dataBean, int position) {
                //分割线
//                AutoLinearLayout divider_linearLayout = holder.getView(R.id.divider_linearLayout);
//                if (position == 0) {
//                    divider_linearLayout.setVisibility(View.GONE);
//                } else {
//                    divider_linearLayout.setVisibility(View.VISIBLE);
//                }
                //查找控件
                CircleImageView user_small_log_image = holder.getView(R.id.user_small_log_image);       //用户头像
                Glide.with(getActivity()).load(dataBean.user_small_log).into(user_small_log_image);     //设置用户头像
                holder.setText(R.id.user_name_tv, dataBean.user_name);                                   //设置用户名
                CheckBox attention_cb = holder.getView(R.id.attention_cb);                              //查找关注按钮
                //查找图片按钮
                AutoLinearLayout three_linearLayout = holder.getView(R.id.three_linearLayout);          //三张图片的父控件
                ImageView left_image = holder.getView(R.id.left_image);                                 //左边图
                ImageView middle_image = holder.getView(R.id.middle_image);                             //中间边图
                ImageView right_image = holder.getView(R.id.right_image);                               //右边图
                left_image.setScaleType(ImageView.ScaleType.FIT_XY);
                middle_image.setScaleType(ImageView.ScaleType.FIT_XY);
                right_image.setScaleType(ImageView.ScaleType.FIT_XY);

                AutoLinearLayout two_linearLayout = holder.getView(R.id.two_linearLayout);              //两张图片的父控件
                ImageView image_left = holder.getView(R.id.image_left);                                 //左边图
                ImageView image_right = holder.getView(R.id.image_right);
                image_left.setScaleType(ImageView.ScaleType.FIT_XY);//右边图
                image_right.setScaleType(ImageView.ScaleType.FIT_XY);//右边图
                Gson gson = new Gson();

                ImageView large_image = holder.getView(R.id.large_image);                               //一张大图
                large_image.setScaleType(ImageView.ScaleType.FIT_XY);
                //获取图片数据
                String source = (String) dataBean.source;
                //判断数据是否为空
                if (!TextUtils.isEmpty(source)) {
                    //解析数据

                    String[] imageArray = gson.fromJson(source, String[].class);
                    if (imageArray.length >= 3) {
                        two_linearLayout.setVisibility(View.GONE);
                        large_image.setVisibility(View.GONE);
                        three_linearLayout.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imageArray[0]).placeholder(R.mipmap.default_three).into(left_image);
                        Glide.with(getActivity()).load(imageArray[1]).placeholder(R.mipmap.default_three).into(middle_image);
                        Glide.with(getActivity()).load(imageArray[2]).placeholder(R.mipmap.default_three).into(right_image);
                    } else if (imageArray.length == 2) {
                        large_image.setVisibility(View.GONE);
                        three_linearLayout.setVisibility(View.GONE);
                        two_linearLayout.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imageArray[0]).placeholder(R.mipmap.default_two).into(image_left);
                        Glide.with(getActivity()).load(imageArray[1]).placeholder(R.mipmap.default_two).into(image_right);
                    } else if (imageArray.length == 1) {
                        three_linearLayout.setVisibility(View.GONE);
                        two_linearLayout.setVisibility(View.GONE);
                        large_image.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imageArray[0]).placeholder(R.mipmap.default_one).into(large_image);
                    }
                }

                holder.setText(R.id.p_title_tv, dataBean.p_title);                   //设置title
                //holder.setText(R.id.p_content_tv,dataBean.p_content);               //设置content
                //  holder.setText(R.id.p_tids_tv, dataBean.p_tids);                     //设置tids

              /*  *//*标签*//*
                TextView p_tids_tv = holder.getView(R.id.p_tids_tv);
                String p_tids = dataBean.p_tids;
                Spanned spanned = Html.fromHtml(p_tids);
                LableBean[] lableBeen = gson.fromJson(spanned.toString(), LableBean[].class);
                p_tids_tv.setText("#" + lableBeen[0].getTname() + "#");*/

                holder.setText(R.id.dianzan_tv, dataBean.p_dig);
                holder.setText(R.id.share_tv, dataBean.p_sharecount);
                holder.setText(R.id.message_tv, dataBean.p_replycount);
            }
        };
    }
    /**
     * 创建fragment对象
     *
     * @param title
     * @return
     */
    public static Fragment getFragment(String title, String nid) {
        TopicParticularsFragment topicParticularsFragment = new TopicParticularsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("nid", nid);
        topicParticularsFragment.setArguments(bundle);
        return topicParticularsFragment;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }

    /**
     * 解决滑动监听事件
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        mSpringView.setEnable(verticalOffset == 0);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        /**
         * 这段代码是为了解决springview 和tabBarLayout中嵌套时上下滚动冲突
         */
        if (isVisibleToUser && this.getContext() != null) {
            //MyApplication application = (MyApplication) this.getContext().getApplicationContext();
            TopicActivity fragmentActivity = (TopicActivity) getActivity();
            if (fragmentActivity.appBarLayout != null) {
                fragmentActivity.appBarLayout.addOnOffsetChangedListener(this);
            }
        } else if (isVisibleToUser && this.getContext() == null) {
            //viewpager中第一页加载的太早,getContext还拿不到,做个延迟
            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (TopicParticularsFragment.this.getContext() != null) {

//                  MyApplication application = (MyApplication) TopicParticularsFragment.this.getContext().getApplicationContext();
//
                        TopicActivity fragmentActivity = (TopicActivity) getActivity();
                        if (fragmentActivity.appBarLayout != null) {
                            fragmentActivity.appBarLayout.addOnOffsetChangedListener(TopicParticularsFragment.this);
                        }
                    }
                }
            });
        }
    }

}
