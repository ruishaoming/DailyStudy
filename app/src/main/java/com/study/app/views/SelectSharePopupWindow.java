package com.study.app.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.study.app.R;


/**
 * Created by 郭艳杰 on 2017/1/5.
 */
public class SelectSharePopupWindow extends PopupWindow {
    private View mMenuView;
    private final TextView tv_qq;
    private final TextView tv_weixin;
    private final TextView tv_sina;
    private final TextView tv_qzone;
    private final TextView tv_friend;

    public SelectSharePopupWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.sharepop, null);
        tv_qq = (TextView) mMenuView.findViewById(R.id.tv_qq);
        tv_weixin = (TextView) mMenuView.findViewById(R.id.tv_weixin);
        tv_sina = (TextView) mMenuView.findViewById(R.id.tv_sina);
        tv_qzone = (TextView) mMenuView.findViewById(R.id.tv_qzone);
        tv_friend = (TextView) mMenuView.findViewById(R.id.tv_friend);
        //设置按钮监听
        tv_qq.setOnClickListener(itemsOnClick);
        tv_weixin.setOnClickListener(itemsOnClick);
        tv_sina.setOnClickListener(itemsOnClick);
        tv_qzone.setOnClickListener(itemsOnClick);
        tv_friend.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.take_photo_anim);


        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.WHITE);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }
}
