package com.example.chen.atguigucode.commom.banner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.banner.demo.BannerLocalActivity;
import com.example.chen.atguigucode.commom.banner.demo.CustomBannerActivity;
import com.example.chen.atguigucode.commom.banner.demo.CustomViewPagerActivity;
import com.example.chen.atguigucode.commom.banner.imageloader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.CubeInTransformer;
import com.youth.banner.transformer.CubeOutTransformer;
import com.youth.banner.transformer.DefaultTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.FlipHorizontalTransformer;
import com.youth.banner.transformer.FlipVerticalTransformer;
import com.youth.banner.transformer.ForegroundToBackgroundTransformer;
import com.youth.banner.transformer.RotateDownTransformer;
import com.youth.banner.transformer.RotateUpTransformer;
import com.youth.banner.transformer.ScaleInOutTransformer;
import com.youth.banner.transformer.StackTransformer;
import com.youth.banner.transformer.TabletTransformer;
import com.youth.banner.transformer.ZoomInTransformer;
import com.youth.banner.transformer.ZoomOutSlideTransformer;
import com.youth.banner.transformer.ZoomOutTranformer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BannerMainActivity extends Activity {


    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.spinner_banner_anim)
    Spinner spinnerBannerAnim;
    @BindView(R.id.spinner_banner_style)
    Spinner spinnerBannerStyle;
    @BindView(R.id.spinner_banner_indicator)
    Spinner spinnerBannerIndicator;
    @BindView(R.id.bt_banner_custom)
    Button btBannerCustom;
    @BindView(R.id.bt_banner_local)
    Button btBannerLocal;
    @BindView(R.id.bt_banner_viewpager)
    Button btBannerViewpager;


    private int H;
    private int W;
    private List<String> images;//图片地址
    private List<String> titles; //图片标题
    private List<String> styleList;// 样式列表
    private List<String> indicatorList;// 指示器列表
    List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_main);
        ButterKnife.bind(this);
        this.mContext = this;

        //初始化标题
        initTitle();

        initData();


        //4. setBanner
        setBanner();

        //5. 设置snipper监听
        setSpinner();
    }


    /**
     * 设置下拉菜单
     */
    private void setSpinner() {
        AdapterView.OnItemSelectedListener animSelectListener = new AnimSpinnerItemSelectListener();
        AdapterView.OnItemSelectedListener indicatorSelectListener = new IndicatorSpinnerItemSelectListener();
        AdapterView.OnItemSelectedListener slyelSelectListener = new StyleSpinnerItemSelectListener();

        spinnerBannerAnim.setOnItemSelectedListener(animSelectListener);
        spinnerBannerStyle.setOnItemSelectedListener(slyelSelectListener);
        spinnerBannerIndicator.setOnItemSelectedListener(indicatorSelectListener);


    }

    private void setBanner() {

        //1. 设置宽高 获取params-> 设置--> 更新

        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = H / 4;
        banner.requestLayout();//        banner.setLayoutParams(layoutParams);


        //2. 设置图片
        banner.setImageLoader(new GlideImageLoader())//图片加载器
                .setImages(images)                          //图片地址
                .setBannerTitles(titles)                    //图片标题
                .setBannerAnimation(transformers.get(0))    //默认动画
                .setBannerStyle(BannerConfig.NOT_INDICATOR) //样式
//                .setOnBannerListener(bannerListener)      //监听
                .start();





        //3 .设置监听

        OnBannerListener bannerListener = new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext, titles.get(position), Toast.LENGTH_SHORT).show();
            }
        };
        banner.setOnBannerListener(bannerListener);
    }


    private void initData() {
        //1.
        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);


        images = Arrays.asList(urls);//图片数据
        titles = Arrays.asList(tips);//标题


        //2. 测量屏幕
        getScreen(this);


        //5. 初始化不同动画方法引用
        transformers.add(DefaultTransformer.class);
        transformers.add(AccordionTransformer.class);
        transformers.add(BackgroundToForegroundTransformer.class);
        transformers.add(ForegroundToBackgroundTransformer.class);
        transformers.add(CubeInTransformer.class);
        transformers.add(CubeOutTransformer.class);
        transformers.add(DepthPageTransformer.class);
        transformers.add(FlipHorizontalTransformer.class);
        transformers.add(FlipVerticalTransformer.class);
        transformers.add(RotateDownTransformer.class);
        transformers.add(RotateUpTransformer.class);
        transformers.add(ScaleInOutTransformer.class);
        transformers.add(StackTransformer.class);
        transformers.add(TabletTransformer.class);
        transformers.add(ZoomInTransformer.class);
        transformers.add(ZoomOutTranformer.class);
        transformers.add(ZoomOutSlideTransformer.class);


    }


    private void initTitle() {
        tvTopTile.setText("Banner");


    }


    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H = dm.heightPixels;
        W = dm.widthPixels;
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

    /**
     *     button点击事件
     */
    @OnClick({
            R.id.bt_banner_custom,
            R.id.bt_banner_local,
            R.id.bt_banner_viewpager
    })
    void itemOnclick(View view){
        switch (view.getId()){
            case R.id.bt_banner_custom:
                startCustomerActivity();//自定义banner
                break;
            case R.id.bt_banner_local:
                startLocalActvity();
                break;

            case R.id.bt_banner_viewpager:
                startViewPagerActivty();
                break;

        }

    }


    /**
     * 自动自定义ViewPager页面
     *
     */
    private void startViewPagerActivty() {
        Intent intent = new Intent(mContext, CustomViewPagerActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("images", (Serializable) images);
        bundle.putSerializable("titles", (Serializable) titles);

        intent.putExtras(bundle);

        startActivity(intent);


    }


    /**
     * 启动加载本地图片页面
     */
    private void startLocalActvity() {
        Intent intent = new Intent(mContext, BannerLocalActivity.class);
        startActivity(intent);


    }


    /**
     * 自动自定义样式页面
     */
    private void startCustomerActivity() {
        Intent intent = new Intent(mContext, CustomBannerActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("images", (Serializable) images);
        bundle.putSerializable("titles", (Serializable) titles);

        intent.putExtras(bundle);
        startActivity(intent);

    }


    /**
     * 1. 动画选择器监听类
     */
    class AnimSpinnerItemSelectListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i < transformers.size()) {
                banner.setBannerAnimation(transformers.get(i));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    /**
     * 2. 样式选择器监听类
     */
    class StyleSpinnerItemSelectListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            //2. banner 设置内置样式
            switch (i) {
                case 0:
                    banner.updateBannerStyle(BannerConfig.NOT_INDICATOR);
                    break;
                case 1:
                    banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                    break;
                case 2:
                    banner.updateBannerStyle(BannerConfig.NUM_INDICATOR);
                    break;
                case 3:
                    banner.updateBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                    break;
                case 4:
                    banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                    break;
                case 5:
                    banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                    break;
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    /**
     * 3. 指示器选择器监听类
     */
    class IndicatorSpinnerItemSelectListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            switch (position) {
                case 0:
                    banner.setIndicatorGravity(BannerConfig.LEFT);
                    break;
                case 1:
                    banner.setIndicatorGravity(BannerConfig.CENTER);
                    break;
                case 2:
                    banner.setIndicatorGravity(BannerConfig.RIGHT);
                    break;
            }
            banner.start();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}

