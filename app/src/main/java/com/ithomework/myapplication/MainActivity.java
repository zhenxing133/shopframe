package com.ithomework.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ithomework.myapplication.adapter.MyListAdapter;
import com.ithomework.myapplication.adapter.MyStickyAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Data> datas = new ArrayList<>();
    private ArrayList<Head> headDatas = new ArrayList<>();
    private MyListAdapter adapter;
    private boolean isScroll = false;
    @InjectView(R.id.linear_layout)
    LinearLayout linearLayout;
    @InjectView(R.id.list_view)
    ListView list_view;
    @InjectView(R.id.stickey_list)
    StickyListHeadersListView stickey_list;
    @InjectView(R.id.ll_title_search)
    LinearLayout ll_title_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        // 先清理状态栏和导航栏的状态，然后强制显示状态栏和导航栏并设置flag模式
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        // 重新添加flag
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // 设置状态栏颜色
        window.setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
       // initRecyclerView();
        initSticky();
        initListView();

    }

    //普通条目数据
     public static class Data{

        public String info;
        //分组操作
        public int headId;
        //当前条目所在下标
        public int index;

    }
    //头条目数据
    public static class Head{
        public String info;
        //点击listview某个头需要知道对应的下标
        public int groupIndex;
    }

    private void initData() {

        //分组条目
        for (int hi = 0; hi < 10; hi++) {
            Head head = new Head();
            head.info = "头：" + hi;
            headDatas.add(head);

        }

        for (int hi = 0; hi < headDatas.size(); hi++) {

            Head head = headDatas.get(hi);

            // 普通条目
            for (int di = 0; di < 10; di++) {
                Data data = new Data();
                data.headId = hi;
                data.info = "普通条目：第" + hi + "组，条目数" + di;
                data.index = hi ;
                if (di == 0) {
                    head.groupIndex = datas.size();
                }
                datas.add(data);

            }
        }
    }

    private void initSticky() {
        stickey_list.setAdapter(new MyStickyAdapter(datas,headDatas,getApplicationContext()));
        stickey_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isScroll = true ;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    Data data = datas.get(firstVisibleItem);
                    //当前置顶的头高亮处理
                    adapter.setSelectedPosition(data.index);
                }

            }
        });

    }

    private void initListView() {
        adapter = new MyListAdapter(headDatas,getApplicationContext());
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedPosition(position);
                Head head = headDatas.get(position);
                stickey_list.setSelection(head.groupIndex);
                isScroll = false ;
            }
        });
    }
}
