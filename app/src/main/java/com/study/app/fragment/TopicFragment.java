package com.study.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.study.app.R;
import com.study.app.activity.TopicActivity;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.bean.TopicInfo;
import com.study.app.designs.GlideImageLoader;
import com.study.app.interfaces.ICallback;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.NetUtils;
import com.study.app.utils.URLUtils;
import com.study.app.views.MyHeader;
import com.study.app.views.ShowingPage;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 芮靖林
 * on 2017/1/14 13:32.
 */

public class TopicFragment extends BaseFragment implements OnBannerClickListener, AdapterView.OnItemClickListener, SpringView.OnFreshListener {
    private SpringView mSringView;
    private Banner mBanner;
    private ListView mMyLv;
    private ListView mHotLv;
    private boolean TopicFragment_isOnlineAndHasNet = true;
    private TopicInfo topicInfo;
    private SpringView mSpringView;

    //进行网路判断
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (NetUtils.isNoNet()) {
            TopicFragment_isOnlineAndHasNet = false;
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
        View rootView = CommonUtils.inflate(R.layout.fragment_topic);
        mSpringView = (SpringView) rootView.findViewById(R.id.topic_springview);
        mSringView = (SpringView) rootView.findViewById(R.id.topic_springview);
        mBanner = (Banner) rootView.findViewById(R.id.topic_banner);
        mBanner.setOnBannerClickListener(this);
        mMyLv = (ListView) rootView.findViewById(R.id.topic_mylist);
        mHotLv = (ListView) rootView.findViewById(R.id.topic_hotlist);
        mMyLv.setFocusable(false);
        mHotLv.setFocusable(false);
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setHeader(new MyHeader(getActivity()));
        mSpringView.setListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //处理数据请求及网络
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                TopicFragment_isOnlineAndHasNet = true;
                getData();
            }
        });
        if (TopicFragment_isOnlineAndHasNet) {
            getData();
        } else {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }

    //请求数据
    private void getData() {
        BaseData baseData = new BaseData();
        baseData.getData(URLUtils.BASE_URL, URLUtils.TOPIC_URL, BaseData.SHORT_TIME, new ICallback() {


            @Override
            public void onResponse(String responseInfo) {
                topicInfo = new Gson().fromJson(responseInfo, TopicInfo.class);
                initData(topicInfo);
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    //初始化数据
    private void initData(final TopicInfo topicInfo) {

        //设置导航轮播图
        List<String> list = new ArrayList<>();
        for (int i = 0; i < topicInfo.getData().getBanner().size(); i++) {
            list.add(topicInfo.getData().getBanner().get(i).getImg());
        }
        //设置样式、加载URL数据源、设置加载方式、设置时长
        mBanner.setBannerAnimation(Transformer.Default).setImages(list).setImageLoader(new GlideImageLoader()).setDelayTime(3000).start();


        //设置热门圈子
        mHotLv.setAdapter(new CommonAdapter<TopicInfo.DataBean.CircleBean>(getActivity(), R.layout.topic_lv_item, topicInfo.getData().getCircle()) {
            @Override
            protected void convert(ViewHolder holder, TopicInfo.DataBean.CircleBean item, int position) {
                ImageView n_small_img = holder.getView(R.id.topic_small_img);
                Glide.with(getActivity()).load(item.getN_small_img()).into(n_small_img);
                holder.setText(R.id.topic_title_tv, item.getN_title());
                holder.setText(R.id.topic_brief_tv, item.getN_brief());
                holder.setText(R.id.topic_user_count_tv, item.getN_user_count() + "人关注");
                holder.setText(R.id.topic_post_count_tv, item.getN_post_count() + "帖子");
                ImageView addAttention_img = holder.getView(R.id.topic_addAttention_img);
                addAttention_img.setVisibility(View.VISIBLE);
            }
        });

        mHotLv.setOnItemClickListener(this);
    }

    /**
     * 轮播图点击事件
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 话题条目点击
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), TopicActivity.class);
        intent.putExtra("nid", topicInfo.getData().getCircle().get(position).getNid());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getData();
        mSpringView.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {

    }
}
