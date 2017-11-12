package com.example.chen.atguigucode.commom.glide;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.glide.adapter.GrideRecycleViewAdapter;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideRecycleViewActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.rv_glide_recycleview)
    RecyclerView rvGlideRecycleview;

    //图片列表数据
    private List<Trailer.TrailersBean> datas;


    private Context mContext ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_recycle_view);
        ButterKnife.bind(this);

        this.mContext =this;

        //inittitl
        initTitle();


        initData();//获取list 数据


        //3. recycleview 设置adapter

        setRecycleViewAdapter();
    }


    /**
     * 3. 为recycleview 设置adapter
     */
    private void setRecycleViewAdapter() {

        final GrideRecycleViewAdapter adapter = new GrideRecycleViewAdapter(datas, mContext);

        rvGlideRecycleview.setAdapter(adapter);

        rvGlideRecycleview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));


        //adapter 设置监听

        adapter.setOnItemClickListener(new GrideRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemTextClickListener(int position, String name, View view) {
                Toast.makeText(mContext, position + ":" + name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemImageClickListener(int position, Trailer.TrailersBean bean, View view) {

                Toast.makeText(mContext, position + ":" + bean.toString(), Toast.LENGTH_SHORT).show();

                //adapter.addItem(position);
            }
        });


    }

    /**
     *
     */
    private void initTitle() {
        tvTopTile.setText("Glide-RecycleView");
    }


    /**
     * intent 中获取数据
     */
    private void initData() {

        List<Trailer.TrailersBean> list = (List<Trailer.TrailersBean>) this.getIntent().getSerializableExtra("list");

        if(list!=null&&list.size()>0) {
            datas = list;
        }
    }
}
