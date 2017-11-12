package com.example.chen.atguigucode.commom.okhttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by chen on 2017/8/14.
 * 使用okhttp utils 获取图片列表显示的adapter
 */

public class OkHttpImageListAdapter extends BaseAdapter {

    private List<Trailer.TrailersBean> datas;
    private Context mContext;

    public OkHttpImageListAdapter(List<Trailer.TrailersBean> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {


        return datas.size();
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
        final ViewHolder viewholder ;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_okhttp_list_image,null);
            viewholder = new ViewHolder(view);

            view.setTag(viewholder);

        } else {
            viewholder = (ViewHolder) view.getTag();
        }

        Trailer.TrailersBean bean = datas.get(i);
        //显示文字
        viewholder.tvName.setText(bean.getMovieName());
        viewholder.tvDesc.setText(bean.getSummary());



        //显示适配图片

        OkHttpUtils
                .get()//
                .url(bean.getCoverImg())//
                .build()//
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //
                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        viewholder.ivIcon.setImageBitmap(response);
                    }
                });
        return view;
    }

    static



    class ViewHolder {
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
