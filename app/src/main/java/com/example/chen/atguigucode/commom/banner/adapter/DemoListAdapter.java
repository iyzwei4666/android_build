package com.example.chen.atguigucode.commom.banner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chen.atguigucode.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/18.
 */

public class DemoListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> datas;

    public DemoListAdapter(Context mContext, List<String> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            view = View.inflate(mContext, R.layout.item_list_banner_demolist, null);
            holder = new ViewHolder(view);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.text.setText(datas.get(i));
        if (i % 2 == 1) {
            holder.text.setBackgroundColor(Color.parseColor("#f5f5f5"));
        } else {
            holder.text.setBackgroundColor(Color.WHITE);
        }
        return view;

    }

    static class ViewHolder {
        @BindView(R.id.text)
        TextView text;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
