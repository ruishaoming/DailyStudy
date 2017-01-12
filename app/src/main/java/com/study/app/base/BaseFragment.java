package com.study.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.app.utils.NetUtils;
import com.study.app.views.ShowingPage;


/**
 * Created by 芮靖林
 * on 2016/12/28 12:01.
 */

public abstract class BaseFragment extends Fragment {
    public ShowingPage showingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        showingPage = new ShowingPage(getActivity()) {
            @Override
            protected View createSuccessView() {
                return BaseFragment.this.createSuccessView();
            }

            @Override
            protected void onLoad() {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        BaseFragment.this.onLoad();
                    }
                }.start();
            }
        };

        return showingPage;
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
