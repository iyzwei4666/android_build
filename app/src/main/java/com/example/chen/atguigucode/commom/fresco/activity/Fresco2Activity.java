package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco2Activity extends Activity {


    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sdv_fresco1_crop)
    SimpleDraweeView sdvFresco1Crop;
    @BindView(R.id.tv_fresco1_explain)
    TextView tvFresco1Explain;
    @BindView(R.id.bt_fresco1_1center)
    Button btFresco11center;
    @BindView(R.id.bt_fresco1_2centercrop)
    Button btFresco12centercrop;
    @BindView(R.id.bt_fresco1_3focuscrop)
    Button btFresco13focuscrop;
    @BindView(R.id.bt_fresco1_4centerinside)
    Button btFresco14centerinside;
    @BindView(R.id.bt_fresco1_5fitcenter)
    Button btFresco15fitcenter;
    @BindView(R.id.bt_fresco1_6fitstart)
    Button btFresco16fitstart;
    @BindView(R.id.bt_fresco1_7fitend)
    Button btFresco17fitend;
    @BindView(R.id.bt_fresco1_8fitxy)
    Button btFresco18fitxy;
    @BindView(R.id.bt_fresco1_9none)
    Button btFresco19none;
    private Context mContext;
    private GenericDraweeHierarchyBuilder builder;
    private String desc;//描述


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco2);
        ButterKnife.bind(this);

        this.mContext = this;

        //initTitle
        tvTopTile.setText("Fresco:图片剪裁");

        initData();



    }

    /**
     * 初始化数据
     *
     */
    private Uri uri ;
    private String url;
    private void initData() {
        //1. 地址
        url = "http://img.taopic.com/uploads/allimg/140113/318739-1401130R04688.jpg";

        builder = new GenericDraweeHierarchyBuilder(getResources());
        //2.
        uri = Uri.parse(url);

        //5. 显示图片:默认样式显示图片
        sdvFresco1Crop.setImageURI(uri);


    }


    @OnClick({R.id.bt_fresco1_1center,
            R.id.bt_fresco1_2centercrop,
            R.id.bt_fresco1_3focuscrop,
            R.id.bt_fresco1_4centerinside,
            R.id.bt_fresco1_5fitcenter,
            R.id.bt_fresco1_6fitstart,
            R.id.bt_fresco1_7fitend,
            R.id.bt_fresco1_8fitxy,
            R.id.bt_fresco1_9none
    })
    public void itemOnclick(View view) {
        switch (view.getId()) {
            case R.id.bt_fresco1_1center :
                tvFresco1Explain.setText(((Button)view).getText().toString()+"居中无缩放");

                //中心裁剪

                GenericDraweeHierarchy hierarchy =  builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER).build();

                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);


                break;
            case R.id.bt_fresco1_2centercrop :
                 desc = "保持宽高比例放大或缩小,使两边都大于等于显示边界,居中显示";
                tvFresco1Explain.setText(((Button)view).getText().toString()+":"+desc);
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP).build();
                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);

                break;
            case R.id.bt_fresco1_3focuscrop :
                desc="根据指定坐标,居中";
                PointF point = new PointF(0,0);

                hierarchy =  builder.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP).setActualImageFocusPoint(point).build();
                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);

                break;
            case R.id.bt_fresco1_4centerinside :
                desc="根据指定坐标,居中";
                tvFresco1Explain.setText(((Button)view).getText().toString()+":"+desc);


                hierarchy =  builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build();
                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);


                break;
            case R.id.bt_fresco1_5fitcenter :
                tvFresco1Explain.setText(((Button)view).getText().toString());
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build();

                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);


                break;
            case R.id.bt_fresco1_6fitstart :
                tvFresco1Explain.setText(((Button)view).getText().toString());
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build();
                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);


                break;
            case R.id.bt_fresco1_7fitend :
                tvFresco1Explain.setText(((Button)view).getText().toString());
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_END).build();

                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);

                break;
            case R.id.bt_fresco1_8fitxy :
                tvFresco1Explain.setText(((Button)view).getText().toString());
                hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);

                break;
            case R.id.bt_fresco1_9none :
                tvFresco1Explain.setText(((Button)view).getText().toString());

                hierarchy = builder.setActualImageScaleType(null).build();
                sdvFresco1Crop.setHierarchy(hierarchy);
                sdvFresco1Crop.setImageURI(uri);

                break;
        }
    }


}
