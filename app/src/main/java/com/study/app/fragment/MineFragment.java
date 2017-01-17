package com.study.app.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.study.app.R;
import com.study.app.activity.DetailsActivity;
import com.study.app.activity.LoginActivity;
import com.study.app.base.BaseFragment;
import com.study.app.designs.TitleBuilder;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.LogUtils;
import com.study.app.utils.NetUtils;
import com.study.app.utils.ToastUtil;
import com.study.app.views.ShowingPage;

/**
 * Created by 芮靖林
 * on 2017/1/11 10:51.
 */

public class MineFragment extends BaseFragment {

    @Override
    protected void createTitleView(ShowingPage showingPage) {
        new TitleBuilder(showingPage).setMiddleText("我的主页", 0).build();
    }

    @Override
    protected boolean isNeedTitle() {
        return true;
    }

    @Override
    protected View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_mine);
        view.findViewById(R.id.rl_my_logedin_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);

    }
}
