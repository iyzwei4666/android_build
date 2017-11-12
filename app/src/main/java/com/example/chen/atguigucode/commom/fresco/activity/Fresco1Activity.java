package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fresco1Activity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sd_fresco_image_view)
    SimpleDraweeView sdFrescoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco1);
        ButterKnife.bind(this);


        tvTopTile.setText("Fresco:带进度条的图片");


        initData();
    }

    private void initData() {


        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources()).setProgressBarImage(new ProgressBarDrawable()).build();
        sdFrescoImageView.setHierarchy(hierarchy);

        Uri uri = Uri.parse("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3305215070,2994410597&fm=173&s=3DE0A6084473E58E715B466A0300607B&w=640&h=480&img.JPEG");
        Uri uri2 =Uri.parse("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        sdFrescoImageView.setImageURI(uri);

    }
}
