package com.study.app.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.R;
import com.study.app.activity.DetailsActivity;
import com.study.app.activity.MainActivity;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.interfaces.ICallback;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.NetUtils;
import com.study.app.views.ShowingPage;

/**
 * Created by 芮靖林
 * on 2017/1/11 10:09.
 */

public class HomeFragment extends BaseFragment {
    private String responseInfo;

    @Override
    protected void onLoad() {
        if (NetUtils.isNoNet()) {
            this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }

        //网络请求
        BaseData baseData = new BaseData();
        baseData.getData("http://www.yulin520.com/", "http://www.yulin520.com/a2a/broadcast/files?sign=7442C54B6DAFB81CEB01588164F3CCA8&ts=1482907765&pageSize=9&page=1", BaseData.SHORT_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                HomeFragment.this.responseInfo = responseInfo;
                //得到请求的数据
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                Toast.makeText(getActivity(), "失败" + errorInfo, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_home);

        return view;
    }
}
