package com.example.chen.atguigucode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.adapter.CommonFrameFragmentAdapter;
import com.example.chen.atguigucode.commom.afinal.AfinalActivity;
import com.example.chen.atguigucode.commom.asynchttp.AsyncHttpActivity;
import com.example.chen.atguigucode.commom.banner.BannerMainActivity;
import com.example.chen.atguigucode.base.BaseFragment;
import com.example.chen.atguigucode.commom.butterknife.ButterKnifeActivity;
import com.example.chen.atguigucode.commom.countdownview.CountdownViewMainActivity;
import com.example.chen.atguigucode.commom.envetbus.EventBusActivity;
import com.example.chen.atguigucode.commom.fresco.FrescoActivity;
import com.example.chen.atguigucode.commom.glide.GlideActivity;
import com.example.chen.atguigucode.commom.imageloader.ImageLoaderActivity;
import com.example.chen.atguigucode.commom.jiecao.JieCaoMainActivity;
import com.example.chen.atguigucode.commom.jsonnative.FastJsonActivity;
import com.example.chen.atguigucode.commom.jsonnative.GsonParseActivity;
import com.example.chen.atguigucode.commom.jsonnative.NativitJsonActivity;
import com.example.chen.atguigucode.commom.okhttp.OkHttpActivity;
import com.example.chen.atguigucode.commom.okhttp.OkHttpUtilsActivity;
import com.example.chen.atguigucode.commom.opendanmaku.OpenDanmakuMainActivity;
import com.example.chen.atguigucode.commom.picaso.PicasoActivity;
import com.example.chen.atguigucode.commom.recycleview.RecycleViewActivity;
import com.example.chen.atguigucode.commom.tablelayout.TablelayoutMainActivity;
import com.example.chen.atguigucode.commom.universalvideoview.UniversalVideoViewActivity;
import com.example.chen.atguigucode.commom.volley.VolleyActivity;
import com.example.chen.atguigucode.commom.xtuils3.XUtilsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chen on 2017/8/12.
 * 1. 常用框架
 */

public class CommonFrameFragment extends BaseFragment {

    @BindView(R.id.lv_main_common)
    ListView lvMainCommon;
    Unbinder unbinder;


    private String[] datas;


    @Override
    protected void initData() {
        datas = new String[]{"AsyncHttp", "OKHttp", "OKHttpUtils", "xUtils3", "NativeJson", "Gson", "FastJson", "Afinal", "Volley",
                "EvenBus", "ButterKnife", "ImageLoader", "Picasso", "RecycleView", "Glide", "Fresco", "UniversalVideoView",
                "JieCaoPlayer", "Banner", "CountdownView", "OpenDanmaku", "TableLayout", "greenDao", "RxJava", "jcvideoplayer", "pulltorefresh", "Expandablelistview", "....."};

        ListAdapter adapter = new CommonFrameFragmentAdapter(mContext, datas);
        lvMainCommon.setAdapter(adapter);
        lvMainCommon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                // String content = ((TextView)view).getText().toString();
                String text = datas[i];

                switchToActivity(text);


            }
        });
    }


    /**
     * 更加选择的item 启动相应activity
     *
     * @param text
     */
    private void switchToActivity(String text) {
        switch (text.toLowerCase()) {
            case "asynchttp"://AsyncHttp

                Intent intent = new Intent(mContext, AsyncHttpActivity.class);
                startActivity(intent);
                break;
            case "okhttp":

                intent = new Intent(mContext, OkHttpActivity.class);
                startActivity(intent);
                break;
            case "okhttputils":

                intent = new Intent(mContext, OkHttpUtilsActivity.class);
                startActivity(intent);
                break;
            case "xutils3":

                intent = new Intent(mContext, XUtilsActivity.class);
                startActivity(intent);
                break;
            case "nativejson": //原生json解析

                intent = new Intent(mContext, NativitJsonActivity.class);
                startActivity(intent);
                break;
            case "gson": //GSON 解析JSON 数据

                intent = new Intent(mContext, GsonParseActivity.class);
                startActivity(intent);
                break;
            case "fastjson": //GSON 解析JSON 数据

                intent = new Intent(mContext, FastJsonActivity.class);
                startActivity(intent);
                break;
            case "afinal": //Afinal

                intent = new Intent(mContext, AfinalActivity.class);
                startActivity(intent);
                break;

            case "volley": //Volley
                intent = new Intent(mContext, VolleyActivity.class);
                startActivity(intent);
                break;
            case "evenbus": //evenBus
                intent = new Intent(mContext, EventBusActivity.class);
                startActivity(intent);
                break;

            case "butterknife": //ButterKnife
                intent = new Intent(mContext, ButterKnifeActivity.class);
                startActivity(intent);
                break;
            case "imageloader": //ImageLoader
                intent = new Intent(mContext, ImageLoaderActivity.class);
                startActivity(intent);
                break;
            case "picasso": //Picasso
                intent = new Intent(mContext, PicasoActivity.class);
                startActivity(intent);
                break;
            case "recycleview": //RecycleView
                intent = new Intent(mContext, RecycleViewActivity.class);
                startActivity(intent);
                break;
            case "glide": //Glide
                intent = new Intent(mContext, GlideActivity.class);
                startActivity(intent);
                break;
            case "fresco": //Fresco
                intent = new Intent(mContext, FrescoActivity.class);
                startActivity(intent);
                break;
            case "universalvideoview": //UniversalVideoView
                intent = new Intent(mContext, UniversalVideoViewActivity.class);
                startActivity(intent);
                break;
            case "jiecaoplayer": //JieCaoPlayer
                intent = new Intent(mContext, JieCaoMainActivity.class);
                startActivity(intent);
                break;

            case "banner": //Banner
                intent = new Intent(mContext, BannerMainActivity.class);
                startActivity(intent);
                break;
            case "countdownview": //CountdownView--倒计时秒杀
                intent = new Intent(mContext, CountdownViewMainActivity.class);
                startActivity(intent);
                break;
            case "opendanmaku": //OpenDanmaku--弹幕
                intent = new Intent(mContext, OpenDanmakuMainActivity.class);
                startActivity(intent);
                break;
            case "tablelayout": //TableLayout--Viewpager
                intent = new Intent(mContext, TablelayoutMainActivity.class);
                startActivity(intent);
                break;


        }


    }


    @Override
    protected View initView() {

        return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View view = View.inflate(mContext, R.layout.fragment_main_commonframe, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }
}
