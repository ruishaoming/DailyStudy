package com.study.app.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.study.app.R;
import com.study.app.activity.MainActivity;
import com.study.app.adapter.HotTypeRvAdapter;
import com.study.app.base.BaseFragment;
import com.study.app.utils.CommonUtils;
import com.study.app.views.HidingScrollListener;
import com.study.app.views.MyHeader;
import com.study.app.views.ShowingPage;

import java.util.ArrayList;

/**
 * Created by 芮靖林
 * on 2017/1/15 21:16.
 */

public class HotTypeFragment extends BaseFragment implements SpringView.OnFreshListener {

    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatBtn;
    private ArrayList<String> datas;

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
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.hot_type_rec);
        mFloatBtn = (FloatingActionButton) rootView.findViewById(R.id.hot_type_FloatingActionButton);
        mSpringView.setHeader(new MyHeader());
        mSpringView.setListener(this);
        mSpringView.setType(SpringView.Type.FOLLOW);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
        showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    private void getData() {
        datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("我 是 item " + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(new HotTypeRvAdapter(getActivity(), datas));

        setFabAnim();
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

    public static Fragment getTitleFromHot(String type) {
        HotTypeFragment fragment = new HotTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titleType", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "刷新---", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadmore() {

    }
}
