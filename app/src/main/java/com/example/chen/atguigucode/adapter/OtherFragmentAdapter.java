package com.example.chen.atguigucode.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chen.atguigucode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/20.
 * <p>
 * 第四个页面:other fragment 中的list对应的adapter
 */

public class OtherFragmentAdapter extends BaseAdapter {

    private String[] datas;
    private Context context;

    public OtherFragmentAdapter(String[] datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas==null?0:datas.length;
    }

    @Override
    public Object getItem(int i) {
        return datas[i];

    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;

        if (view == null) {
            view = View.inflate(context, R.layout.item_otherfragment_list_textview, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }


        holder.textview.setText(datas[i]);

        return  view;


    }

    static class ViewHolder {
        @BindView(R.id.textview)
        TextView textview;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
