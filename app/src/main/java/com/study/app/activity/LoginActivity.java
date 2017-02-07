package com.study.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.study.app.R;
import com.study.app.base.BaseActivity;
import com.study.app.base.BaseData;
import com.study.app.base.BaseShowingPageActivity;
import com.study.app.bean.LoginInfo;
import com.study.app.designs.TitleBuilder;
import com.study.app.interfaces.ICallback;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.URLUtils;
import com.study.app.views.ShowingPage;

import java.util.HashMap;

/**
 * 芮靖林
 * 登录Activity
 */
public class LoginActivity extends BaseActivity {

    private EditText num;
    private EditText pwd;
    private TextView nonet_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initTitle();
        num = (EditText) findViewById(R.id.login_et_uname);
        pwd = (EditText) findViewById(R.id.login_et_pwd);
        nonet_tv = (TextView) findViewById(R.id.login_tv_nonet);
    }

    private void initTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.btn_back).setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setMiddleText("登录", 0).build();
    }


    public void login_btn(View view) {
        BaseData baseData = new BaseData();
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", CommonUtils.getText(num));
        map.put("password", CommonUtils.getText(pwd));
        map.put("dosubmit", "1");
        baseData.postData(false, true, URLUtils.BASE_URL, URLUtils.LOGIN_URL, BaseData.NO_TIME, map, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                nonet_tv.setVisibility(View.INVISIBLE);

                LoginInfo loginInfo = new Gson().fromJson(responseInfo, LoginInfo.class);
                if ("200".equals(loginInfo.getStatus())) {
                    //登录成功
                } else {
                    //登录错误提示用户
                    Toast.makeText(LoginActivity.this, loginInfo.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String errorInfo) {
                //显示网络连接错误
                nonet_tv.setVisibility(View.VISIBLE);
            }
        });
    }


    //注册
    public void regist_btn(View view) {

    }
}
