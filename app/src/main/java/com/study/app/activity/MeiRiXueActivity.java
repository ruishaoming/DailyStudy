package com.study.app.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.study.app.R;
import com.study.app.adapter.MeiRiXueAdapter;
import com.study.app.base.BaseData;
import com.study.app.base.BaseShowingPageActivity;
import com.study.app.bean.MeiRiXueBean;
import com.study.app.designs.TitleBuilder;
import com.study.app.divider.DividerItemDecoration;
import com.study.app.interfaces.ICallback;
import com.study.app.interfaces.OnItemClickListener;
import com.study.app.utils.URLUtils;
import com.study.app.views.ShowingPage;

import java.util.HashMap;
import java.util.List;

public class MeiRiXueActivity extends BaseShowingPageActivity implements SpringView.OnFreshListener {
    HashMap<String,String> map = new HashMap<>();
    private String url;
    private MeiRiXueBean viewPagerBean;
    private RecyclerView meirixue_recyclerView;
    private ImageView meirixue_img;
    private SpringView meirixue_springView;
    private TextView meirixue_tv;
    private List<MeiRiXueBean.DataListBean.ListBean> list;
    private MeiRiXueAdapter adapter;
    private TextView meirixue_name;

    @Override
    protected void onLoad() {
        BaseData baseData = new BaseData();
        map.put("aid",url);
        baseData.postData(false, false, URLUtils.ViewPager_BASEURL, URLUtils.ViewPager_URL, BaseData.SHORT_TIME, map, new ICallback() {

            @Override
            public void onResponse(String responseInfo) {
                Gson gson = new Gson();
                viewPagerBean = gson.fromJson(responseInfo, MeiRiXueBean.class);
                list = viewPagerBean.dataList.get(0).list;
                MeiRiXueActivity.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData();
                    }
                });
            }

            @Override
            public void onFailure(String errorInfo) {
               MeiRiXueActivity.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    @Override
    protected void createTitleView() {
        new TitleBuilder(MeiRiXueActivity.this).setLeftImageRes(R.mipmap.btn_back).setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setMiddleImageRes(R.mipmap.meirixue).setMostRightImageRes(R.mipmap.abc_ic_search_api_mtrl_alpha).setMostRightImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected View createSuccessView() {
        url = getIntent().getStringExtra("url");
        View view = LayoutInflater.from(this).inflate(R.layout.activity_mei_ri_xue, null);
        initView(view);

        meirixue_springView.setHeader(new DefaultHeader(this));
        meirixue_springView.setListener(this);
        meirixue_springView.setType(SpringView.Type.FOLLOW);
        return view;
    }

    private void setData() {
        Glide.with(MeiRiXueActivity.this).load(viewPagerBean.toppic).into(meirixue_img);
        meirixue_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        meirixue_tv.setText(viewPagerBean.desc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        meirixue_name.setText(viewPagerBean.dataList.get(0).title);
        meirixue_recyclerView.setLayoutManager(linearLayoutManager);
        meirixue_recyclerView.addItemDecoration(new DividerItemDecoration(MeiRiXueActivity.this, LinearLayoutManager.VERTICAL));
        adapter = new MeiRiXueAdapter(this, list);
        meirixue_recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MeiRiXueActivity.this,DetailsActivity.class);
                String cid = viewPagerBean.dataList.get(0).list.get(position).cid;
                intent.putExtra("url",cid);
                startActivity(intent);
            }
        });

    }

    private void initView(View view) {
        meirixue_recyclerView = (RecyclerView) view.findViewById(R.id.meirixue_recyclerView);
        meirixue_img = (ImageView) view.findViewById(R.id.meirixue_img);
        meirixue_springView = (SpringView) view.findViewById(R.id.meirixue_springView);
        meirixue_tv = (TextView) view.findViewById(R.id.meirixue_tv);
        meirixue_name = (TextView) view.findViewById(R.id.meirixue_name);
    }

    @Override
    public void onRefresh() {
        stop();

    }
    public void stop(){
        meirixue_springView.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {

    }
}
