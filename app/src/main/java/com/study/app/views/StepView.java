package com.study.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by ${郭艳杰} on 2017/1/7.
 */

public class StepView extends View {

    private Random random;
    String text = "默认文字";

    public StepView(Context context) {
        super(context);
        init();
    }

    public StepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        random = new Random();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,getMeasuredHeight()/2,paint);
//        Rect rect = new Rect();
//        paint.setTextSize(20);
//        paint.getTextBounds(text,0,text.length(),rect);
//        paint.setColor(Color.RED);
//        canvas.drawText(text,getMeasuredWidth()/2-rect.width()/2,getMeasuredHeight()/2+rect.height()/2,paint);


    }
    public void setText(String text){
        this.text = text;
    }

}
