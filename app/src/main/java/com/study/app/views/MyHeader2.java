package com.study.app.views;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaoinstan.springview.container.BaseHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.study.app.R;

/**
 * Created by PC on 2017/1/22.
 */

public class MyHeader2 extends BaseHeader implements SpringView.DragHander{

    private ImageView head_arrow;
    private TextView text;
    private ImageView animation;
    private RotateAnimation rotate;

    //获取Header
    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view=inflater.inflate(R.layout.header,viewGroup);
        head_arrow = (ImageView) view.findViewById(R.id.header_arrow);
        text = (TextView) view.findViewById(R.id.header_hint_textview);
        animation = (ImageView) view.findViewById(R.id.head_animation);

        return view;
    }
    //拖拽开始前回调
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onPreDrag(View rootView) {
        text.setText("下拉刷新");
        head_arrow.setImageAlpha(200);
        head_arrow.setImageResource(R.mipmap.xlistview_arrow);
        animation.setVisibility(View.GONE);
    }

    //手指拖拽过程中不断回调，dy为拖拽的距离，可以根据拖动的距离添加拖动过程动画
    @Override
    public void onDropAnim(View rootView, int dy) {

    }
    //手指拖拽过程中每次经过临界点时回调，upORdown是向上经过还是向下经过
    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        //向上经过
        if(upORdown)
        {
            setRotate();
        }else{
            RotateAnimation rotate=new RotateAnimation(0,-180, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            rotate.setDuration(200);
            rotate.setFillAfter(true);
            rotate.setRepeatMode(Animation.RESTART);
            head_arrow.startAnimation(rotate);
            text.setText("松开加载数据");
        }
    }

    //拉动超过临界点后松开时回调
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onStartAnim() {
        animation.setVisibility(View.VISIBLE);
        animation.setImageResource(R.drawable.header_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) animation.getDrawable();
        animationDrawable.start();
        head_arrow.setImageAlpha(0);
        setRotate();
        text.setText("");

    }

    //头部已经全部弹回时回调
    @Override
    public void onFinishAnim() {

    }
    /**
     * 这个方法用于设置当前View的临界高度(limit hight)，即拉动到多少会被认定为刷新超作，而没到达该高度则不会执行刷新
     * 返回值大于0才有效，如果<=0 则设置为默认header的高度
     * 默认返回0
     */
    @Override
    public int getDragLimitHeight(View rootView) {
        return 100;
    }

    /**
     * 这个方法用于设置下拉最大高度(max height)，无论怎么拉动都不会超过这个高度
     * 返回值大于0才有效，如果<=0 则默认600px
     * 默认返回0
     */
    @Override
    public int getDragMaxHeight(View rootView) {
        return 200;
    }

    /**
     * 这个方法用于设置下拉弹动高度(spring height)，即弹动后停止状态的高度
     * 返回值大于0才有效，如果<=0 则设置为默认header的高度
     * 默认返回0
     */
    @Override
    public int getDragSpringHeight(View rootView) {
        return 0;
    }

    public void setRotate()
    {
        RotateAnimation rotate= new RotateAnimation(-180,0, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(200);
        rotate.setFillAfter(true);
        rotate.setRepeatMode(Animation.RESTART);
        head_arrow.startAnimation(rotate);
        text.setText("下拉刷新");
    }

}