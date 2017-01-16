package com.study.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.study.app.R;
import com.study.app.base.BaseFragment;
import com.study.app.utils.CommonUtils;
import com.study.app.views.ShowingPage;

/**
 * Created by 芮靖林
 * on 2017/1/15 21:16.
 */

public class HotTypeFragment extends BaseFragment {

    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatBtn;

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
        //        https://github.com/makovkastar/FloatingActionButton
        mFloatBtn = (FloatingActionButton) rootView.findViewById(R.id.hot_type_FloatingActionButton);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    public static Fragment getTitleFromHot(String type) {
        HotTypeFragment fragment = new HotTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titleType", type);
        fragment.setArguments(bundle);
        return fragment;
    }
}
