package com.study.app.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.application.MyApplication;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.interfaces.ICallback;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.NetUtils;
import com.study.app.views.ShowingPage;

/**
 * Created by 芮靖林
 * on 2017/1/11 10:52.
 */

public class QuanFragment extends BaseFragment {
    private String responseInfo;
    private boolean CourseFragment_isOnline = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (NetUtils.isNoNet()) {
            CourseFragment_isOnline = false;
        }
    }

    @Override
    protected void onLoad() {
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                CourseFragment_isOnline = true;
                initData();
            }
        });
        initData();
    }

    @Override
    protected View createSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText("圈子：" + responseInfo);
        return textView;
    }

    private void initData() {

        if (CourseFragment_isOnline) {
            BaseData baseData = new BaseData();
            baseData.getData("http://www.yulin520.com/", "http://www.yulin520.com/a2a/broadcast/files?sign=7442C54B6DAFB81CEB01588164F3CCA8&ts=1482907765&pageSize=9&page=1", BaseData.SHORT_TIME, new ICallback() {
                @Override
                public void onResponse(String responseInfo) {
                    QuanFragment.this.responseInfo = responseInfo;
                    showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                }

                @Override
                public void onFailure(String errorInfo) {
                    Toast.makeText(getActivity(), "失败" + errorInfo, Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }
}
