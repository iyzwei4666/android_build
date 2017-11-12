package com.example.chen.atguigucode.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chen.atguigucode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/13.
 * <p>
 * 常用框架页面的adapter
 */

public class CommonFrameFragmentAdapter extends BaseAdapter {


    private final Context mContex;
    private final String[] datas;

    public CommonFrameFragmentAdapter(Context mContex, String[] datas) {
        this.mContex = mContex;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
             view = View.inflate(mContex, R.layout.item_commonfragment_list_textview, null);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.textview.setText(datas[i]);


        if(i%2==1) {
            viewHolder.textview.setBackgroundColor(Color.parseColor("#f5f5f5"));
        }else {
            viewHolder.textview.setBackgroundColor(Color.WHITE);

        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.textview)
        TextView textview;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}