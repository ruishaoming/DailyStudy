package com.study.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.study.app.R;
import com.study.app.adapter.RecommedAdapter;
import com.study.app.adapter.StudyAdapter;
import com.study.app.base.BaseData;
import com.study.app.base.BaseFragment;
import com.study.app.bean.HomeBean;
import com.study.app.designs.TitleBuilder;
import com.study.app.divider.DividerItemDecoration;
import com.study.app.interfaces.ICallback;
import com.study.app.interfaces.IOnResetShowingPage;
import com.study.app.interfaces.OnItemClickListener;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.NetUtils;
import com.study.app.views.MyGridView;
import com.study.app.views.RootViewPager;
import com.study.app.views.ShowingPage;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 芮靖林
 * on 2017/1/11 10:09.
 */

public class HomeFragment extends BaseFragment implements SpringView.OnFreshListener {

    private boolean CourseFragment_isOnline = true;
    private SpringView springView;
    private HomeBean homeBean;
    private LinearLayout ll_dot;
    private List<HomeBean.DataBean.SliderBean> slider;
    ArrayList<String> imageListUrl = new ArrayList<>();
    ArrayList<ImageView> dotList = new ArrayList<>();
    int[] dotArray = {R.mipmap.sliding, R.mipmap.slidingr};
    HashMap<String, String> map = new HashMap<>();
    private RootViewPager roolViewPager;
    private View view;
    private MyGridView myGridView;
    private List<HomeBean.DataBean.HotcategoryBean> hotcategoryList;
    private ImageView idea_img;
    private ImageView sw_img;
    private ImageView run_img;
    private TextView idea_name;
    private TextView idea_title;
    private TextView sw_name;
    private TextView sw_title;
    private TextView run_name;
    private TextView run_title;
    private MyGridView gv;
    private MyGridView gv_hotcourse;
    private MyGridView gv_recommend;
    private RecyclerView recommend_recyclerView;
    private RecommedAdapter recommedAdapter;
    private RecyclerView study_recyclerView;
    private StudyAdapter studyAdapter;
    private ConvenientBanner convenientBanner;
    private String[] imageArr;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (NetUtils.isNoNet()) {
            CourseFragment_isOnline = false;
        }
    }


    @Override
    protected View createSuccessView() {
        view = CommonUtils.inflate(R.layout.fragment_home);
        initView(view);
        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setListener(this);
        springView.setType(SpringView.Type.FOLLOW);

        return view;
    }

    private void initView(View view) {
        springView = (SpringView) view.findViewById(R.id.home_fragment_springView);
        myGridView = (MyGridView) view.findViewById(R.id.myGrideView);
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        idea_img = (ImageView) view.findViewById(R.id.idea_img);
        idea_name = (TextView) view.findViewById(R.id.idea_name);
        idea_title = (TextView) view.findViewById(R.id.idea_title);
        gv = (MyGridView) view.findViewById(R.id.gv);
        gv_hotcourse = (MyGridView) view.findViewById(R.id.gv_hotcourse);
        gv_recommend = (MyGridView) view.findViewById(R.id.gv_recommend);
        recommend_recyclerView = (RecyclerView) view.findViewById(R.id.recommend_recyclerView);
        study_recyclerView = (RecyclerView) view.findViewById(R.id.study_recyclerView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
        //重新加载的监听
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                CourseFragment_isOnline = true;
                getData();
            }
        });
    }

    @Override
    protected void createTitleView(ShowingPage showingPage) {
        new TitleBuilder(showingPage).setTitleBackGroundColor(getResources().getColor(R.color.colorPrimary)).setMiddleImageRes(R.mipmap.meirixue).setMiddleTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "HOME", Toast.LENGTH_SHORT).show();
            }
        }).setMostRightImageRes(R.mipmap.abc_ic_search_api_mtrl_alpha).setMostRightImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //是否需要Title
    @Override
    protected boolean isNeedTitle() {
        return true;
    }

    //初始化数据
    private void getData() {
//网络请求
        BaseData baseData = new BaseData();
        map.put("a", "indexv9");
        map.put("c", "index");
//        a=indexv9&c=index
        baseData.postData(false, false, "http://www.meirixue.com", "/api.php", BaseData.TEN_MINUTE_TIME, map, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                //得到请求的数据并解析
                Gson gson = new Gson();
                homeBean = gson.fromJson(responseInfo, HomeBean.class);
                //得到请求的数据
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 轮播图
                        initViewPager();
                        // 全部分类
                        initGridView();
                        //最强思路
                        initIdea();
                        //热门课程
                        initHotCourse();
                        //推荐
                        initRecommed();
                        //大家都在学
                        initStudy();
                    }
                });

            }

            @Override
            public void onFailure(String errorInfo) {
                Toast.makeText(getActivity(), "失败" + errorInfo, Toast.LENGTH_SHORT).show();
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    //大家都在学
    private void initStudy() {
        List<HomeBean.DataBean.IndexothersBean> indexothers = homeBean.data.indexothers;
        study_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        study_recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        studyAdapter = new StudyAdapter(getActivity(), indexothers);
        study_recyclerView.setAdapter(studyAdapter);
        studyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    //推荐
    private void initRecommed() {
        List<HomeBean.DataBean.IndexrecommendBean.TopBean> topList = homeBean.data.indexrecommend.top;
        gv_recommend.setAdapter(new CommonAdapter<HomeBean.DataBean.IndexrecommendBean.TopBean>(getActivity(), R.layout.gv_recommend_item, topList) {
            @Override
            protected void convert(ViewHolder viewHolder, HomeBean.DataBean.IndexrecommendBean.TopBean item, int position) {
                ImageView gv_recommend_img = viewHolder.getView(R.id.gv_recommend_img);
                gv_recommend_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getActivity()).load(item.course_pic).into(gv_recommend_img);
            }
        });
        ArrayList<HomeBean.DataBean.IndexrecommendBean.ListviewBean> listview = (ArrayList<HomeBean.DataBean.IndexrecommendBean.ListviewBean>) homeBean.data.indexrecommend.listview;
        recommend_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recommend_recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recommedAdapter = new RecommedAdapter(getActivity(), listview);
        recommend_recyclerView.setAdapter(recommedAdapter);
        recommedAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

    }

    //热门课程
    private void initHotCourse() {
        final List<HomeBean.DataBean.HotcourseBean> hotcourseList = homeBean.data.hotcourse;
        gv_hotcourse.setAdapter(new CommonAdapter<HomeBean.DataBean.HotcourseBean>(getActivity(), R.layout.gv_hotcourse_item, hotcourseList) {
            @Override
            protected void convert(ViewHolder viewHolder, HomeBean.DataBean.HotcourseBean item, int position) {
                ImageView gv_hotcourse_img = viewHolder.getView(R.id.gv_hotcourse_img);
                TextView gv_hotcourse_name = viewHolder.getView(R.id.gv_hotcourse_name);
                TextView gv_hotcourse_title = viewHolder.getView(R.id.gv_hotcourse_title);
                Glide.with(getActivity()).load(item.img).into(gv_hotcourse_img);
                gv_hotcourse_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                gv_hotcourse_name.setText(item.name);
                gv_hotcourse_title.setText(item.title);
            }
        });

    }

    //最强思路
    private void initIdea() {
        List<HomeBean.DataBean.AdlistBean> adlist = homeBean.data.adlist;
        final List<HomeBean.DataBean.AdlistBean> ideaList = new ArrayList<>();
        for (int i = 0; i < adlist.size(); i++) {
            if (i > 0 && i < 3) {
                ideaList.add(new HomeBean.DataBean.AdlistBean(adlist.get(i).name, adlist.get(i).title, adlist.get(i).img));
            }
        }
        idea_name.setText(adlist.get(0).name);
        idea_title.setText(adlist.get(0).title);
        Glide.with(getActivity()).load(adlist.get(0).img).into(idea_img);
        final int[] color = {getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.yellow)};
        gv.setAdapter(new CommonAdapter<HomeBean.DataBean.AdlistBean>(getActivity(), R.layout.gv_item, ideaList) {
            @Override
            protected void convert(ViewHolder viewHolder, HomeBean.DataBean.AdlistBean item, int position) {
                ImageView gv_img = viewHolder.getView(R.id.gv_img);
                TextView gv_name = viewHolder.getView(R.id.gv_name);
                TextView gv_title = viewHolder.getView(R.id.gv_title);
                Glide.with(getActivity()).load(ideaList.get(position).img).into(gv_img);
                gv_name.setText(ideaList.get(position).name);
                gv_title.setText(ideaList.get(position).title);
                gv_name.setTextColor(color[position]);
            }
        });


    }

    //全部分类
    private void initGridView() {
        hotcategoryList = homeBean.data.hotcategory;
        ArrayList<HomeBean.DataBean.HotcategoryBean> hotList = new ArrayList<>();
        for (int i = 0; i < hotcategoryList.size(); i++) {
            if (i < 6) {
                hotList.add(new HomeBean.DataBean.HotcategoryBean(hotcategoryList.get(i).cname, hotcategoryList.get(i).img));
            }
        }

        myGridView.setAdapter(new CommonAdapter<HomeBean.DataBean.HotcategoryBean>(getActivity(), R.layout.mygridview_item, hotList) {
            @Override
            protected void convert(ViewHolder viewHolder, HomeBean.DataBean.HotcategoryBean item, int position) {

                TextView myGrideView_text = viewHolder.getView(R.id.myGrideView_text);
                ImageView myGrideView_img = viewHolder.getView(R.id.myGrideView_img);
                Glide.with(getActivity()).load(hotcategoryList.get(position).img).into(myGrideView_img);
                myGrideView_text.setText(hotcategoryList.get(position).cname);
            }

        });
    }

    //轮播图
    private void initViewPager() {
        slider = homeBean.data.slider;
        imageArr = new String[slider.size()];
        for (int i = 0; i < slider.size(); i++) {
//            imageListUrl.add(slider.get(i).img);
            imageArr[i] = slider.get(i).img;
        }
        convenientBanner.startTurning(4000);
//        convenientBanner.setPageTransformer(new AccordionTransformer());
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(imageArr)).setPageIndicator(dotArray).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

    }

    class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(getActivity()).load(imageArr[position]).into(imageView);
        }
    }

    @Override
    public void onRefresh() {
        stop();
    }

    public void stop() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {

    }
}
