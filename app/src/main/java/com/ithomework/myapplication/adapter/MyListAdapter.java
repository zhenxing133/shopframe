package com.ithomework.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ithomework.myapplication.MainActivity;
import com.ithomework.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class MyListAdapter extends BaseAdapter {

    private List<MainActivity.Head> heads = new ArrayList<>();
    private Context mContext;
    private int selectedPosition;
    public MyListAdapter(ArrayList<MainActivity.Head> headDatas, Context applicationContext) {
        this.heads = headDatas;
        mContext = applicationContext;
    }

    @Override
    public int getCount() {
        return heads.size();
    }

    @Override
    public Object getItem(int position) {
        return heads.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_view);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv.setText(heads.get(position).info);
        viewHolder.tv.setTextColor(Color.BLUE);
        if (selectedPosition == position) {
            viewHolder.tv.setBackgroundColor(Color.WHITE);
        } else {
            viewHolder.tv.setBackgroundColor(Color.GRAY);
        }
        return convertView;
    }


    public void setSelectedPosition(int selectedPosition) {
        if(this.selectedPosition==selectedPosition){
            return ;
        }
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView tv ;
    }
}
