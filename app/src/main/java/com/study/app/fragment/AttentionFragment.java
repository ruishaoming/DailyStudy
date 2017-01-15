package com.study.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.study.app.base.BaseFragment;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.NetUtils;
import com.study.app.views.ShowingPage;

/**
 * Created by 芮靖林
 * on 2017/1/14 13:35.
 */

public class AttentionFragment extends BaseFragment {

    private boolean AttentionFragment_isOnlineAndHasNet = true;

    //进行网路判断
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (NetUtils.isNoNet()) {
            AttentionFragment_isOnlineAndHasNet = false;
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
        TextView textView = new TextView(getActivity());
        textView.setText("关注Fragment");
        textView.setTextSize(50);
        showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //处理数据请求及网络
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                AttentionFragment_isOnlineAndHasNet = true;
                getData();
            }
        });
        if (AttentionFragment_isOnlineAndHasNet) {
            getData();
        }else {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }

    //请求数据
    private void getData() {

    }
}
