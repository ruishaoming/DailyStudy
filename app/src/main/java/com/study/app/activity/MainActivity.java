package com.study.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.study.app.R;
import com.study.app.base.BaseActivity;
import com.study.app.base.BaseData;
import com.study.app.factory.FragmentFactory;
import com.study.app.views.LazyViewPager;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    public RadioGroup radioGroup;
    private LazyViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initViewPager();
        BaseData baseData = new BaseData();
        Map<String, String> map = new HashMap<>();
        map.put("tid", -1 + "");
        map.put("page", 1 + "");

//        map.put("key","96efc220a4196fafdfade0c9d1e897ac");
//        map.put("qq","111111111");
        //"http://japi.juhe.cn/","qqevaluate/qq"
//        baseData.postData("http://www.meirixue.com/", "api.php", BaseData.SHORT_TIME, map, new ICallback() {
//            @Override
//            public void onResponse(String responseInfo) {
//                Toast.makeText(MainActivity.this, "post成功" + responseInfo, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(String errorInfo) {
//                Toast.makeText(MainActivity.this, "post失败" + errorInfo, Toast.LENGTH_SHORT).show();
//            }
//        });

//        baseData.getData("http://www.yulin520.com/", "http://www.yulin520.com/a2a/broadcast/files?sign=7442C54B6DAFB81CEB01588164F3CCA8&ts=1482907765&pageSize=9&page=1",BaseData.SHORT_TIME, new ICallback() {
//            @Override
//            public void onResponse(String responseInfo) {
//                Toast.makeText(MainActivity.this, "成功" + responseInfo, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(String errorInfo) {
//                Toast.makeText(MainActivity.this, "失败" + errorInfo, Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    //初始化ViewPager
    private void initViewPager() {
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (rb.getId()==checkedId) {
                        vp.setCurrentItem(i);
                    }
                }
            }
        });
        vp.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (i == position) {
                        rb.setChecked(true);
                    } else {
                        rb.setChecked(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化控件
    private void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.main_bottom_rg);
        vp = (LazyViewPager) findViewById(R.id.main_vp);
    }

    //监听连续两次返回退出
    private long waitTime = 1200;
    private long touchTime = 0;

    //监听程序退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                touchTime = currentTime;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
