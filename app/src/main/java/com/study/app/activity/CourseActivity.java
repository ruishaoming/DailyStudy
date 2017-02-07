package com.study.app.activity;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.study.app.R;
import com.study.app.adapter.RecycleViewAdapter;
import com.study.app.base.BaseData;
import com.study.app.base.BaseShowingPageActivity;
import com.study.app.bean.CourseList;
import com.study.app.bean.ThreeCourseBean;
import com.study.app.designs.TitleBuilder;
import com.study.app.interfaces.ICallback;
import com.study.app.utils.CommonUtils;
import com.study.app.utils.URLUtils;
import com.study.app.views.ShowingPage;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseActivity extends BaseShowingPageActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private static final String TAG = "CourseActivity";
    private TextView tv_name;
    private TextView tv_choose;
    private TextView tv_order;
    private RecyclerView recycleView_course;
    private String id;
    private String name;
    private PopupWindow popupWindow;
    private ListView pop_listview1;
    private ListView pop_listview2;
    private ListView pop_listview3;
    private ThreeCourseBean[] threeCourseBean;
    private List<ThreeCourseBean.NodesBean> nodes;
    private List<ThreeCourseBean.NodesBean.Nodes2Bean> nodes2;
    private CommonAdapter<ThreeCourseBean.NodesBean> m1Adapter;
    private CommonAdapter<ThreeCourseBean.NodesBean.Nodes2Bean> m2Adapter;
    private ThreeCourseBean.NodesBean.Nodes2Bean.Menu3Bean menu3;
    private Map<String, String> map;
    private RecycleViewAdapter recycleViewAdapter;
    private TextView tv_choose1;
    private TextView tv_choose2;
    private TextView tv_order1;
    private TextView tv_order2;
    private TextView tv_order3;
    private CourseList courseList;
    private List<CourseList.DatalistBean> datalistAll = new ArrayList<>();

    @Override
    protected void onLoad() {
        showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected void createTitleView() {
        new TitleBuilder(showingPage).setMiddleText("课程列表", 0).setMostRightImageRes(R.mipmap.glass).setMostRightImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).setLeftImageRes(R.mipmap.btn_back).build();

    }

    @Override
    protected View createSuccessView() {
//       View view = CommonUtils.inflate(R.layout.activity_course);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        View view = LayoutInflater.from(this).inflate(R.layout.activity_course, null);
        initView(view);
        initData();
        getData();
        return view;
    }

    private void initData() {
        tv_name.setText(name);
        tv_choose.setText("筛选");
        tv_order.setText("排序");
    }

    //post请求
    private void getData() {
        //获取三级列表数据
        getListData();
        //获取分类列表数据
        getCourseData();
    }

    private void getCourseData() {
        new BaseData().getData(URLUtils.BASE_URL, URLUtils.THREECOURSE_URL, BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                Log.i("~~~", responseInfo);
                threeCourseBean = new Gson().fromJson(responseInfo, ThreeCourseBean[].class);

                //设置成功的视图
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorInfo) {
                //设置失败的视图
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    private void getListData() {
        map = new HashMap<String, String>();
        map.put("cid", id);
       // map.put("p", "2");
        new BaseData().postData(false, false, URLUtils.BASE_URL, URLUtils.COURSELIST_RUL_POST, BaseData.SHORT_TIME, map, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                datalistAll.clear();
                courseList = new Gson().fromJson(responseInfo, CourseList.class);
                List<CourseList.DatalistBean> datalist = courseList.getDatalist();
                datalistAll.addAll(datalist);
                //设置适配器
                recycleViewAdapter = new RecycleViewAdapter(CourseActivity.this, datalistAll);
                recycleView_course.setAdapter(recycleViewAdapter);

                //设置成功的视图
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }

            @Override

            public void onFailure(String errorInfo) {
                //设置失败的视图
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    private void initView(View view) {
        recycleView_course = (RecyclerView) view.findViewById(R.id.recycleView_course);
        recycleView_course.setLayoutManager(new LinearLayoutManager(CourseActivity.this));
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_choose = (TextView) view.findViewById(R.id.tv_choose);
        tv_order = (TextView) view.findViewById(R.id.tv_order);
        tv_name.setOnClickListener(this);
        tv_choose.setOnClickListener(this);
        tv_order.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_name:
                View popView1 = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
                popupWindow = new PopupWindow(popView1, LinearLayout.LayoutParams.MATCH_PARENT, 800);
                pop_listview1 = (ListView) popView1.findViewById(R.id.pop_listview1);
                pop_listview2 = (ListView) popView1.findViewById(R.id.pop_listview2);
                pop_listview3 = (ListView) popView1.findViewById(R.id.pop_listview3);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    setBackgroundAlpha(0.3f);
                    popupWindow.showAsDropDown(tv_name);
                    CommonAdapter<ThreeCourseBean> mAdapter = new CommonAdapter<ThreeCourseBean>(this, android.R.layout.simple_list_item_1, Arrays.asList(threeCourseBean)) {
                        @Override
                        protected void convert(ViewHolder viewHolder, ThreeCourseBean item, int position) {
                            viewHolder.setText(android.R.id.text1, threeCourseBean[position].getMenu().getCategory_name());
                        }
                    };
                    pop_listview1.setAdapter(mAdapter);
                    for (int i = 0; i < threeCourseBean.length; i++) {
                        nodes = threeCourseBean[i].getNodes();
                    }
                    m1Adapter = new CommonAdapter<ThreeCourseBean.NodesBean>(this, android.R.layout.simple_list_item_1, nodes) {

                        @Override
                        protected void convert(ViewHolder viewHolder, ThreeCourseBean.NodesBean item, int position) {
                            viewHolder.setText(android.R.id.text1, nodes.get(position).getMenu2().getCategory_name());
                        }
                    };
                    pop_listview2.setAdapter(m1Adapter);

                    for (int i = 0; i < nodes.size(); i++) {
                        nodes2 = nodes.get(i).getNodes2();
                    }
                    m2Adapter = new CommonAdapter<ThreeCourseBean.NodesBean.Nodes2Bean>(this, android.R.layout.simple_list_item_1, nodes2) {

                        @Override
                        protected void convert(ViewHolder viewHolder, ThreeCourseBean.NodesBean.Nodes2Bean item, int position) {
                            viewHolder.setText(android.R.id.text1, nodes2.get(position).getMenu3().getCategory_name());
                        }
                    };
                    for (int i = 0; i < nodes2.size(); i++) {
                        menu3 = nodes2.get(i).getMenu3();

                    }
                    pop_listview3.setAdapter(m2Adapter);
                    pop_listview1.setOnItemClickListener(this);
                    pop_listview2.setOnItemClickListener(this);
                    pop_listview3.setOnItemClickListener(this);

                }
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackgroundAlpha(1.0f);
                    }
                });
                break;
            case R.id.tv_choose:
                View popView2 = CommonUtils.inflate(R.layout.popchoos_item);
                tv_choose1 = (TextView) popView2.findViewById(R.id.tv_choose1);
                tv_choose2 = (TextView) popView2.findViewById(R.id.tv_choose2);
                tv_choose1.setOnClickListener(this);
                tv_choose2.setOnClickListener(this);
                popupWindow = new PopupWindow(popView2, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                setBackgroundAlpha(0.3f);
                popupWindow.showAsDropDown(tv_choose);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackgroundAlpha(1.0f);
                    }
                });

                break;
            case R.id.tv_order:
                View popView3 = CommonUtils.inflate(R.layout.poporder_item);
                tv_order1 = (TextView) popView3.findViewById(R.id.tv_order1);
                tv_order2 = (TextView) popView3.findViewById(R.id.tv_order2);
                tv_order3 = (TextView) popView3.findViewById(R.id.tv_order3);
                tv_order1.setOnClickListener(this);
                tv_order2.setOnClickListener(this);
                tv_order3.setOnClickListener(this);
                popupWindow = new PopupWindow(popView3, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                setBackgroundAlpha(0.3f);
                popupWindow.showAsDropDown(tv_choose);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackgroundAlpha(1.0f);
                    }
                });
                break;
            //免费
            case R.id.tv_choose1:
                tv_choose.setText(tv_choose1.getText().toString());
                popupWindow.dismiss();
                datalistAll.clear();
                for (int i = 0; i < courseList.getDatalist().size(); i++) {
                    String course_price = courseList.getDatalist().get(i).getCourse_price();
                    if (("0.00").equals(course_price)) {
                        datalistAll.add(courseList.getDatalist().get(i));
                    }
                }
                recycleViewAdapter.notifyDataSetChanged();
                break;
            //收费
            case R.id.tv_choose2:
                tv_choose.setText(tv_choose2.getText().toString());
                popupWindow.dismiss();
                datalistAll.clear();
                for (int i = 0; i < courseList.getDatalist().size(); i++) {
                    String course_price = courseList.getDatalist().get(i).getCourse_price();
                    if (!("0.00").equals(course_price)) {
                        datalistAll.add(courseList.getDatalist().get(i));
                    }
                }
                recycleViewAdapter.notifyDataSetChanged();
                break;
            //综合排序
            case R.id.tv_order1:
                tv_order.setText(tv_order1.getText().toString());
                break;
            //最新更新
            case R.id.tv_order2:
                tv_order.setText(tv_order2.getText().toString());
                break;
            //学习人数
            case R.id.tv_order3:
                tv_order.setText(tv_order3.getText().toString());
                break;
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long i) {

//        ThreeCourseBean.NodesBean.Nodes2Bean.Menu3Bean menu3 = nodes2.get(position).getMenu3();

        switch (parent.getId()) {
            case R.id.pop_listview1:
                nodes.clear();
                nodes.addAll(threeCourseBean[position].getNodes());
                m1Adapter.notifyDataSetChanged();
                break;
            case R.id.pop_listview2:
                nodes2.clear();
                nodes2.addAll(nodes.get(position).getNodes2());
                m2Adapter.notifyDataSetChanged();
                break;
            case R.id.pop_listview3:
                id = menu3.getId();
                Log.i("~~", id);
                getListData();
                popupWindow.dismiss();
                break;

        }
    }


    //设置背景色透明的方法
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
        attributes.alpha = bgAlpha;
        this.getWindow().setAttributes(attributes);

    }

}
