package com.study.app.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.study.app.R;
import com.study.app.activity.DetailsActivity;
import com.study.app.base.BaseFragment;
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

    }

    @Override
    protected boolean isNeedTitle() {
        return false;
    }

    @Override
    protected View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_mine);
        Button needNet = (Button) view.findViewById(R.id.needNet);
        Button noNet = (Button) view.findViewById(R.id.noNet);
        needNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DetailsActivity.class));
            }
        });

        noNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
