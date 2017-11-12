package com.example.chen.atguigucode.commom.picaso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.example.chen.atguigucode.commom.picaso.adapter.PicasoImgListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 *  *
 *  总结: 使用Intent 传递类对应的数据的步骤
 *  1. ActivityA :
 *          //使用bundler 传递数据到下一个intent (当前activity需要implement Serializable)
 *          Bundle bundle = new Bundle();
 *          bundle.putSerializable("list", (Serializable) list);
 *          intent.putExtras(bundle);
 *          startActivity(intent);
 *
 * 2. ActivityB:
 *          Intent intent = this.getIntent();
 *          list = (List<Trailer.TrailersBean>) intent.getSerializableExtra("list");
 *
 *
 *
 *
 */



public class PicasonListviewActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.lv_picaso_img_list)
    ListView lvPicasoImgList;


    //待显示数据

    List<Trailer.TrailersBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picason_listview);


        ButterKnife.bind(this);

        initTitle();
        initData();
    }

    /**
     * 初始化数据:
     * 从intent 中取出存放的list 数据
     */
    private void initData() {
         Intent intent = this.getIntent();
         list = (List<Trailer.TrailersBean>) intent.getSerializableExtra("list");

        if(this.list.isEmpty()) {
            Toast.makeText(this, "没有初始化数据", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "初始化数据成功", Toast.LENGTH_SHORT).show();

        PicasoImgListAdapter adapter = new PicasoImgListAdapter(list,PicasonListviewActivity.this);


        lvPicasoImgList.setAdapter(adapter);


    }

    private void initTitle() {
        tvTopTile.setText("Picasso-ListView");

    }



}
