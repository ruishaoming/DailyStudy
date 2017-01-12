package com.study.app.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.study.app.R;
import com.study.app.application.MyApplication;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.NetUtils;


/**
 * Created by 芮靖林
 * on 2016/12/28.
 */
public abstract class ShowingPage extends FrameLayout implements View.OnClickListener {

    /**
     * 定义状态
     */

    public static final int STATE_UNLOAD = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_LOAD_ERROR = 3;
    public static final int STATE_LOAD_EMPTY = 4;
    public static final int STATE_LOAD_SUCCESS = 5;

    public int currentState = STATE_LOADING;//得到当前的状态

    private View showingpage_load_empty;
    private View showingpage_loading;
    private View showingpage_load_error;
    private View showingpage_unload;
    private View showingpage_success;

    private final LayoutParams params;
    private IOnResetShowingPage iOnResetShowingPage;

    public ShowingPage(Context context) {
        super(context);

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        if (showingpage_load_empty == null) {
            showingpage_load_empty = CommonUtils.inflate(R.layout.showingpage_load_empty);
            this.addView(showingpage_load_empty, params);
        }

        if (showingpage_loading == null) {
            showingpage_loading = CommonUtils.inflate(R.layout.showingpage_loading);
            this.addView(showingpage_loading, params);
        }

        if (showingpage_load_error == null) {
            showingpage_load_error = CommonUtils.inflate(R.layout.showingpage_load_error);
            TextView tv_reload = (TextView) showingpage_load_error.findViewById(R.id.showing_error_tv_reset);
            tv_reload.setOnClickListener(this);
            this.addView(showingpage_load_error, params);
        }

        if (showingpage_unload == null) {
            showingpage_unload = CommonUtils.inflate(R.layout.showingpage_unload);
            this.addView(showingpage_unload, params);
        }

        showPage();

        onLoad();
    }

    /**
     * 对外提供方法，设置当前状态
     *
     * @param stateType
     */
    public void showCurrentPage(StateType stateType) {
        this.currentState = stateType.getCurrentState();
        showPage();
    }

    private void showPage() {
        //在主线程执行
        CommonUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPageOnUI();
            }
        });
    }

    private void showPageOnUI() {
        /**
         * 选择当前的状态进行显示
         */
        if (showingpage_unload != null) {
            showingpage_unload.setVisibility(currentState == STATE_UNLOAD ? View.VISIBLE : View.GONE);
        }

        if (showingpage_load_empty != null) {
            showingpage_load_empty.setVisibility(currentState == STATE_LOAD_EMPTY ? View.VISIBLE : View.GONE);
        }

        if (showingpage_load_error != null) {
            showingpage_load_error.setVisibility(currentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);
        }

        if (showingpage_loading != null) {
            showingpage_loading.setVisibility(currentState == STATE_LOADING ? View.VISIBLE : View.GONE);
        }

        if (showingpage_success == null && currentState == STATE_LOAD_SUCCESS) {
            showingpage_success = createSuccessView();
            this.addView(showingpage_success);
        }

        if (showingpage_success != null) {
            showingpage_success.setVisibility(currentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    //获取成功界面（每一个成功的界面都不同）
    protected abstract View createSuccessView();

    //数据加载
    protected abstract void onLoad();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //重新请求
            case R.id.showing_error_tv_reset:
//                if (currentState != STATE_LOADING)
//                    currentState = STATE_LOADING;
//                showPage();
//                onLoad();
                if (!NetUtils.isNoNet() && iOnResetShowingPage != null) {
                    iOnResetShowingPage.onReset(v);
                }
                break;
        }
    }

    public void setIOnResetShowingPage(IOnResetShowingPage iOnResetShowingPage) {
        this.iOnResetShowingPage = iOnResetShowingPage;
    }

    /**
     * 定义枚举类，限制状态类型
     */
    public enum StateType {
        STATE_LOADING(2), STATE_LOAD_ERROR(3), STATE_LOAD_EMPTY(4), STATE_LOAD_SUCCESS(5);
        private final int currentState;

        public int getCurrentState() {
            return currentState;
        }

        StateType(int currentState) {
            this.currentState = currentState;
        }
    }
}
