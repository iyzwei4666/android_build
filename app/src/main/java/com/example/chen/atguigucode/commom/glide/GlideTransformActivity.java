package com.example.chen.atguigucode.commom.glide;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.glide.adapter.GrideTransformRecycleViewAdapter;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideTransformActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.rv_glide_transform_recycle)
    RecyclerView rvGlideTransformRecycle;

    private List<Trailer.TrailersBean> list;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_transform);
        ButterKnife.bind(this);

        this.mContext = this;

        //1. 初始化标题
        initTitle();


        //2. 初始化数据
        intiData();


        //3. recycleview 设置adapter

        setAdapter();
    }


    /**
     * 为recycleList 设置adapter
     */
    private void setAdapter() {

        GrideTransformRecycleViewAdapter adapter = new GrideTransformRecycleViewAdapter(list,mContext);

        rvGlideTransformRecycle.setAdapter(adapter);
        rvGlideTransformRecycle.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

    }

    private void intiData() {
        if (this.getIntent().getSerializableExtra("list") != null) {
            list = (List<Trailer.TrailersBean>) this.getIntent().getSerializableExtra("list");
        }
    }

    private void initTitle() {

        tvTopTile.setText("Glide 图片转换");
    }
}
