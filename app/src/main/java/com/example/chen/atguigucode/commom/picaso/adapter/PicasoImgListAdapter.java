package com.example.chen.atguigucode.commom.picaso.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/15.

 *
 *
 *
 *
 *
 */

public class PicasoImgListAdapter extends BaseAdapter {
    private List<Trailer.TrailersBean> list;
    private Context mConext;

    public PicasoImgListAdapter(List<Trailer.TrailersBean> list, Context mConext) {
        this.list = list;
        this.mConext = mConext;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder viewHolder ;
        if (view == null) {
            view = View.inflate(mConext, R.layout.item_picaso_list_image, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Trailer.TrailersBean bean = list.get(i);
        //设置数据
        viewHolder.tvName.setText(bean.getMovieName());
        viewHolder.tvDesc.setText(bean.getSummary());

        //2. 使用picaso 设置图片


        Picasso.with(mConext)
                .load(bean.getCoverImg())
                .placeholder(R.drawable.atguigu_logo)//加载过程中显示的图片
                .error(R.drawable.video_default) //加载失败显示
                .into(viewHolder.ivIcon);

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.rl_image)
        RelativeLayout rlImage;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_desc)
        TextView tvDesc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
