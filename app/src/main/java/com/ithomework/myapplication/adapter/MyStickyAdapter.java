package com.ithomework.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ithomework.myapplication.MainActivity;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Administrator on 2017/6/7.
 */

public class MyStickyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private List<MainActivity.Data> datas = new ArrayList<>();
    private List<MainActivity.Head> headDatas = new ArrayList<>();
    private Context mContext;
    public MyStickyAdapter(ArrayList<MainActivity.Data> listBody, ArrayList<MainActivity.Head> listHead, Context applicationContext) {
        this.datas = listBody;
        this.headDatas = listHead;
        mContext = applicationContext;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        TextView tv= new TextView(mContext);
        MainActivity.Data data = datas.get(position);
        MainActivity.Head head = headDatas.get(data.index);
        tv.setText(head.info);
        tv.setTextSize(16);
        tv.setBackgroundColor(Color.GRAY);
        return tv;
    }

    @Override
    public long getHeaderId(int position) {
        //依据position获取普通条目
        MainActivity.Data bodyData = datas.get(position);
        return bodyData.headId;
    }

    ////////////////////////////////普通条目////////////////////////////////////

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv= new TextView(mContext);
        tv.setText(datas.get(position).info);
        tv.setTextColor(Color.GRAY);
        tv.setTextSize(14);
        return tv;
    }
}
