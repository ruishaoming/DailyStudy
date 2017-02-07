package com.study.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.app.R;
import com.study.app.base.BaseData;
import com.study.app.utils.CommonUtils;

/**
 * Created by ${郭艳杰} on 2017/1/16.
 */

public class CommentFragment extends Fragment {


    private TextView zonghe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = CommonUtils.inflate(R.layout.comment_item);
        initView(view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
    }

    private void getData() {

        BaseData baseData = new BaseData();
        //baseData.postData(null,null,);

    }

    private void initView(View view) {
        zonghe = (TextView) view.findViewById(R.id.zonghe);

    }


}
