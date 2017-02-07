
package com.study.app.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.study.app.R;
import com.study.app.base.BaseActivity;
import com.study.app.base.BaseData;
import com.study.app.bean.TopicParticularsBean;
import com.study.app.designs.TitleBuilder;
import com.study.app.fragment.TopicParticularsFragment;
import com.study.app.interfaces.ICallback;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.URLUtils;

import java.util.HashMap;

public class TopicActivity extends BaseActivity {

    private TitleBuilder titleBuilder;
    private ImageView user_image;
    private TextView n_title_tv;
    private TextView n_brief_tv;
    private CheckBox n_user_count_cb;
    private CheckBox n_post_count_cb;
    private CheckBox update_attention_cb;
    private TabLayout title_TabLayout;
    private ViewPager tooic_content_viewPager;
    private String nid;
    private String[] titles = {"最新", "最热"};
    private ImageView image_bg;
    public static AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        initViews();
        getData();
    }

    private void getData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("nid", nid);
        hashMap.put("order", "1");
        hashMap.put("page", 1 + "");
        new BaseData().postData(false, false, URLUtils.BASE_URL, URLUtils.TOPIC_ITEM, BaseData.SHORT_TIME, hashMap, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                Gson gson = new Gson();
                TopicParticularsBean topicParticularsBean = gson.fromJson(responseInfo, TopicParticularsBean.class);
//                //为控件复制
                setDatas(topicParticularsBean);
            }

            @Override
            public void onFailure(String errorInfo) {

            }
        });
    }

    /**
     * 头部控件复制
     *
     * @param topicParticularsBean
     */
    private void setDatas(TopicParticularsBean topicParticularsBean) {
        titleBuilder.setMiddleText(topicParticularsBean.data.n_title, 0).build();
        Glide.with(this).load(topicParticularsBean.data.n_big_img).into(image_bg);
        image_bg.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load(topicParticularsBean.data.n_small_img).into(user_image);
        n_title_tv.setText(topicParticularsBean.data.n_title);
        n_brief_tv.setText(topicParticularsBean.data.n_brief);
        n_user_count_cb.setText(topicParticularsBean.data.n_user_count + "");
        n_post_count_cb.setText(topicParticularsBean.data.n_post_count + "");

    }

    /**
     * 初始化View
     */
    private void initViews() {
        titleBuilder = new TitleBuilder(this);
        titleBuilder.setLeftImageRes(R.mipmap.btn_back).setRightImageRes(R.mipmap.post_publish).setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.finishActivity(TopicActivity.this);
            }
        }).setRightImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TopicActivity.this, "编辑中", Toast.LENGTH_SHORT).show();
            }
        }).build();
        nid = getIntent().getStringExtra("nid");

        image_bg = (ImageView) findViewById(R.id.image_bg);                 //北京图
        user_image = (ImageView) findViewById(R.id.user_image);             //用户头像
        n_title_tv = (TextView) findViewById(R.id.n_title_tv);              //用户名
        n_brief_tv = (TextView) findViewById(R.id.n_brief_tv);              //介绍
        n_user_count_cb = (CheckBox) findViewById(R.id.n_user_count_cb);    // n_user_count总数
        n_post_count_cb = (CheckBox) findViewById(R.id.n_post_count_cb);    //n_post_count 总数
        update_attention_cb = (CheckBox) findViewById(R.id.update_attention_image);     //关注取消按钮

        title_TabLayout = (TabLayout) findViewById(R.id.top_Title_TabLayout);               //TabLayout
        tooic_content_viewPager = (ViewPager) findViewById(R.id.tooic_content_viewPager);   //ViewPager
        tooic_content_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(title_TabLayout));
        initTabLayoutViewPager();
        title_TabLayout.setupWithViewPager(tooic_content_viewPager);

    }

    private void initTabLayoutViewPager() {
        tooic_content_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TopicParticularsFragment.getFragment(titles[position], nid);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
    }
}

