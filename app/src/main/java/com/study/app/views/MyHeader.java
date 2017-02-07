package com.study.app.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liaoinstan.springview.container.BaseHeader;
import com.study.app.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by 芮靖林
 * on 2017/1/16 10:14.
 */

public class MyHeader extends BaseHeader {

    private final Context context;
    private TextView headerTitle;
    private final int ROTATE_ANIM_DURATION = 180;
    private RotateAnimation mRotateUpAnim;
    private RotateAnimation mRotateDownAnim;
    private ProgressBar progressBar;
    private ImageView headerArrow;
    private AutoLinearLayout line;

    public MyHeader(Context context) {
        this.context = context;

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.sp_header, viewGroup, true);
        line = (AutoLinearLayout) view.findViewById(R.id.header_line);
        headerArrow = (ImageView) view.findViewById(R.id.header_iv);
        headerTitle = (TextView) view.findViewById(R.id.header_title);
        progressBar = (ProgressBar) view.findViewById(R.id.header_progressbar);
        headerArrow.setImageResource(R.mipmap.xlistview_arrow);
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
    }

    @Override
    public void onDropAnim(View rootView, int dy) {

    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (!upORdown) {
            headerTitle.setText("松开刷新数据");
            if (headerArrow.getVisibility() == View.VISIBLE)
                headerArrow.startAnimation(mRotateUpAnim);
        } else {
            headerTitle.setText("下拉刷新");
            if (headerArrow.getVisibility() == View.VISIBLE)
                headerArrow.startAnimation(mRotateDownAnim);
        }
    }

    @Override
    public void onStartAnim() {
        headerTitle.setText("正在刷新");
        line.setVisibility(View.GONE);
        headerArrow.clearAnimation();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        line.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
