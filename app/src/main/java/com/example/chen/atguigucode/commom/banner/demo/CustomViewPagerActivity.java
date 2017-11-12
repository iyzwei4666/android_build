package com.example.chen.atguigucode.commom.banner.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.banner.imageloader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomViewPagerActivity extends Activity implements OnBannerListener {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    private int H;
    private List<String> images;
    private List<String> titles;


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);
        ButterKnife.bind(this);

        mContext = this;

        //1.
        initTitle();


        //2.
        initData();


        //3.
        initBanner();

    }

    /**
     * 1. 初始化标题
     */
    private void initTitle() {
        tvTopTile.setText("Banner:自定义ViewPager");

    }

    private void initData() {
        images = (List<String>) this.getIntent().getSerializableExtra("images");
        titles = (List<String>) this.getIntent().getSerializableExtra("titles");
        H = getScreen(mContext).get("width");

    }


    public Map<String, Integer> getScreen(Context aty) {
        Map<String, Integer> scrennSize = new HashMap<>();
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        scrennSize.put("height", height);
        scrennSize.put("width", width);
        return scrennSize;
    }


    /**
     * 设置banner
     */
    private void initBanner() {


        banner.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, H / 2));
        //简单使用
        banner.setImages(images)
                .setBannerTitles(titles)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(mContext, titles.get(position), Toast.LENGTH_SHORT).show();

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
