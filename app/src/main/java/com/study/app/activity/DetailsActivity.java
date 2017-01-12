package com.study.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.R;
import com.study.app.base.BaseData;
import com.study.app.base.BaseShowingPageActivity;
import com.study.app.fragment.QuanFragment;
import com.study.app.interfaces.ICallback;
import com.study.app.views.ShowingPage;

/**
 * 芮靖林：
 * 详情Activity
 */
public class DetailsActivity extends BaseShowingPageActivity {

    private String responseInfo;

    @Override
    protected void onLoad() {
        BaseData baseData = new BaseData();
        baseData.getData("http://www.yulin520.com/", "http://www.yulin520.com/a2a/broadcast/files?sign=7442C54B6DAFB81CEB01588164F3CCA8&ts=1482907765&pageSize=9&page=1", BaseData.SHORT_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                DetailsActivity.this.responseInfo = responseInfo;
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                Toast.makeText(DetailsActivity.this, "DetailsActivity::::" + errorInfo, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected View createSuccessView() {
        TextView textView = new TextView(this);
        textView.setText("DetailsActivity::::::::：" + responseInfo);
        return textView;
    }
}
