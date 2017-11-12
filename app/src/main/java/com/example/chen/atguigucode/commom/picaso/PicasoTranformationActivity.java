package com.example.chen.atguigucode.commom.picaso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.example.chen.atguigucode.commom.picaso.adapter.PicasoImgListTransformAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片转换
 */
public class PicasoTranformationActivity extends Activity {

    private List<Trailer.TrailersBean> list;


    private Context mContext ;


    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.lv_picaso_img_list_transform)
    ListView lvPicasoImgListTransform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picaso_tranformation);
        ButterKnife.bind(this);

        mContext = PicasoTranformationActivity.this;

        initTitle();

        initData();

    }

    /**
     *
     */
    private void initData() {
        Intent intent = this.getIntent();
        list = (List<Trailer.TrailersBean>) intent.getSerializableExtra("list");

        if(list.isEmpty()) {
            return;
        }


        PicasoImgListTransformAdapter adapter = new PicasoImgListTransformAdapter(mContext, list);
        lvPicasoImgListTransform.setAdapter(adapter);


    }

    private void initTitle() {
        tvTopTile.setText("Picasso图片转换");
    }



}
