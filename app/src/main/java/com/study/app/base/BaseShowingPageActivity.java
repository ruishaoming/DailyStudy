package com.study.app.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.NetUtils;
import com.study.app.views.ShowingPage;

/**
 * Created by 芮靖林
 * on 2017/1/12 09:11.
 */

public abstract class BaseShowingPageActivity extends BaseActivity {

    private ShowingPage showingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showingPage = new ShowingPage(this) {
            @Override
            protected View createSuccessView() {

                Toast.makeText(BaseShowingPageActivity.this, "得到成功的视图----", Toast.LENGTH_SHORT).show();
                return BaseShowingPageActivity.this.createSuccessView();
            }

            @Override
            protected void onLoad() {
                if (NetUtils.isNoNet()) {
                    Toast.makeText(BaseShowingPageActivity.this, "没网啦----", Toast.LENGTH_SHORT).show();
                    showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
                }
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        BaseShowingPageActivity.this.onLoad();
                    }
                }.start();
            }
        };

        //点击重置
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                onLoad();
            }
        });
    }

    //继续抽象给继承自自己的Fragment
    protected abstract void onLoad();

    protected abstract View createSuccessView();

    public void showCurrentPage(ShowingPage.StateType stateType) {
        //调用showingPage的方法
        if (showingPage != null) {
            showingPage.showCurrentPage(stateType);
        }
    }
}
