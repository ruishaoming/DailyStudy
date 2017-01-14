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
            public View setSuccessView() {
                return createSuccessView();
            }

            @Override
            public boolean needTitleView() {
                return isNeedTitle();
            }
        };

        createTitleView(showingPage);
        return showingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract void createTitleView(ShowingPage showingPage);

    protected abstract boolean isNeedTitle();

    protected abstract View createSuccessView();

    public void showCurrentPage(ShowingPage.StateType stateType) {
        //调用showingPage的方法
        if (showingPage != null) {
            showingPage.setCurrentState(stateType);
        }
    }
}
