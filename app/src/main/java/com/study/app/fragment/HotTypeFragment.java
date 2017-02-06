package com.study.app.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;

import com.study.app.R;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.bean.HotInfo;
import com.study.app.bean.LableBean;
import com.study.app.interfaces.ICallback;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.URLUtils;
import com.study.app.views.HidingScrollListener;
import com.study.app.views.MyHeader;
import com.study.app.views.ShowingPage;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 芮靖林
 * on 2017/1/15 21:16.
 */

public class HotTypeFragment extends BaseFragment implements SpringView.OnFreshListener {

    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatBtn;
    private int page = 1;
    private ArrayList<HotInfo.DataBean> listAll = new ArrayList<>();
    private CommonAdapter adapter;

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
        mSpringView = (SpringView) rootView.findViewById(R.id.hot_type_springview);
        mSpringView.setListener(this);
        mSpringView.setHeader(new MyHeader(getActivity()));
        mSpringView.setFooter(new DefaultFooter(getActivity()));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.hot_type_rec);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFloatBtn = (FloatingActionButton) rootView.findViewById(R.id.hot_type_FloatingActionButton);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
    }

    private void getData() {
        String tid = getArguments().getString("hot_tid", "77");
        HashMap<String, String> map = new HashMap<>();
        map.put("tid", tid);
        map.put("page", page + "");
        BaseData baseData = new BaseData();
        baseData.postData(false, false, URLUtils.BASE_URL, URLUtils.HOT_URL, BaseData.SHORT_TIME, map, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                HotInfo hotInfo = new Gson().fromJson(responseInfo, HotInfo.class);
                initData(hotInfo);
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });

    }

    private void initData(final HotInfo hotInfo) {

        listAll.addAll(hotInfo.getData());
//        if (adapter == null) {

            adapter = new CommonAdapter<HotInfo.DataBean>(getActivity(), R.layout.hot_type_rv_item, listAll) {

                @Override
                protected void convert(ViewHolder holder, HotInfo.DataBean dataBean, int position) {

                    //查找控件
                    CircleImageView user_small_log_image = holder.getView(R.id.user_small_log_image);       //用户头像
                    Glide.with(getActivity()).load(dataBean.getUser_small_log()).into(user_small_log_image);     //设置用户头像
                    holder.setText(R.id.user_name_tv, dataBean.getUser_name());                                   //设置用户名
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
                    String source = (String) dataBean.getSource();
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

                    holder.setText(R.id.p_title_tv, dataBean.getP_title());                   //设置title
                    //holder.setText(R.id.p_content_tv,dataBean.p_content);               //设置content
                    //  holder.setText(R.id.p_tids_tv, dataBean.p_tids);                     //设置tids

                /*标签*/
                    TextView p_tids_tv = holder.getView(R.id.p_tids_tv);
                    String p_tids = dataBean.getP_tids();
                    Spanned spanned = Html.fromHtml(p_tids);
                    LableBean[] lableBeen = gson.fromJson(spanned.toString(), LableBean[].class);
                    p_tids_tv.setText("#"+lableBeen[0].getTname()+"#");

                    holder.setText(R.id.dianzan_tv, dataBean.getP_dig());
                    holder.setText(R.id.share_tv, dataBean.getP_sharecount());
                    holder.setText(R.id.message_tv, dataBean.getP_replycount());
                }
            };
            mRecyclerView.setAdapter(adapter);
//        } else {
//            adapter.notifyDataSetChanged();
//        }
        CommonUtils.setFabAnim(getActivity(),mRecyclerView,mFloatBtn);
//        setFabAnim();
    }

    //设置FloatingButton显示 隐藏的动画
    private void setFabAnim() {
        mRecyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                Resources resources = getActivity().getResources();
                DisplayMetrics dm = resources.getDisplayMetrics();
                int height = dm.heightPixels;
                mFloatBtn.animate()
                        .translationY(height - mFloatBtn.getHeight())
                        .setInterpolator(new AccelerateInterpolator(2))
                        .setDuration(500)
                        .start();
            }

            @Override
            public void onShow() {
                mFloatBtn.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).setDuration(500).start();
            }
        });
    }

    //获取tid
    public static Fragment getTitleTid(String tid) {
        HotTypeFragment fragment = new HotTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("hot_tid", tid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onRefresh() {
        page = 1;
        listAll.clear();
        getData();
        stop();
    }

    public void stop() {
        mSpringView.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {
        page++;
        getData();
        stop();
    }

}
