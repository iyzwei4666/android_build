package com.example.chen.atguigucode.commom.banner.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.banner.imageloader.GlideImageLoader;
import com.example.chen.atguigucode.commom.banner.utils.ScreenUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomBannerActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.banner1)
    Banner banner1;
    @BindView(R.id.banner2)
    Banner banner2;
    @BindView(R.id.banner3)
    Banner banner3;
    private List<String> images;
    private List<String> titles;
    private Context mContext;
    private int H;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        ButterKnife.bind(this);
        mContext = this;


        initTitle();
        initData();

        initBanner();


    }

    /**
     * 初始化数据
     */
    private void initData() {

        H = ScreenUtils.getScreenH(mContext);

        images = (List<String>) this.getIntent().getSerializableExtra("images");
        titles = (List<String>) this.getIntent().getSerializableExtra("titles");


    }

    private void initTitle() {
//        初始化标题
        tvTopTile.setText("Banner 自定义样式");

    }

    /**
     * 3. 初始化banner
     */
    private void initBanner() {

        if (images.isEmpty() || titles.isEmpty()) {
            return;
        }


        ViewGroup.LayoutParams params1 = banner1.getLayoutParams();
        params1.height = H/4;
        ViewGroup.LayoutParams params2 = banner2.getLayoutParams();
        params2.height = H/4;
        ViewGroup.LayoutParams params3 = banner3.getLayoutParams();
        params3.height = H/4;


        banner1.setLayoutParams(params1);//banner1.requestLayout();
        banner2.setLayoutParams(params2);
        banner3.setLayoutParams(params3);


        banner1.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner2.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner3.setImages(images)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();

    }


}
