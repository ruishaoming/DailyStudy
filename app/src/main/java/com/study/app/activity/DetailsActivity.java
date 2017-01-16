package com.study.app.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.R;
import com.study.app.base.BaseData;
import com.study.app.base.BaseShowingPageActivity;
import com.study.app.designs.TitleBuilder;
import com.study.app.fragment.QuanFragment;
import com.study.app.interfaces.ICallback;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.CommonUtils;
import com.study.app.views.ShowingPage;

/**
 * 芮靖林：
 * 详情Activity
 */
public class DetailsActivity extends BaseShowingPageActivity {

    private String responseInfo;
    private TextView textView;

    @Override
    protected void onLoad() {
        BaseData baseData = new BaseData();
        baseData.getData("http://www.yulin520.com/", "http://www.yulin520.com/a2a/broadcast/files?sign=7442C54B6DAFB81CEB01588164F3CCA8&ts=1482907765&pageSize=9&page=1", BaseData.SHORT_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                DetailsActivity.this.responseInfo = responseInfo;
                textView.setText("DetailsActivity::::::::：" + responseInfo);
                DetailsActivity.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                DetailsActivity.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    @Override
    protected void createTitleView() {
        new TitleBuilder(showingPage).setTitleBackGroundColor(Color.RED).setLeftImageRes(R.mipmap.btn_back).setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected View createSuccessView() {
        textView = new TextView(this);

        return textView;
    }

}
