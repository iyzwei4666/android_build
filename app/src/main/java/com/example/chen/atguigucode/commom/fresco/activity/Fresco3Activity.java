package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco3Activity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sdv_fresco1_crop)
    SimpleDraweeView sdvFresco1Crop;
    @BindView(R.id.bt_fresco3_1round)
    Button btFresco31round;
    @BindView(R.id.bt_fresco3_2round_angle)
    Button btFresco32roundAngle;
    private Context mContext;
    private GenericDraweeHierarchyBuilder builder;
    private GenericDraweeHierarchy hierarchy;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco3);
        ButterKnife.bind(this);

        this.mContext = this;

        tvTopTile.setText("Fresco3");

        initTitle();

        //初始化一个builder 用于生成对象
        builder = new GenericDraweeHierarchyBuilder(getResources());

        //3. initData();
        uri = Uri.parse("http://img.taopic.com/uploads/allimg/140326/235113-1403260U22059.jpg");
        sdvFresco1Crop.setImageURI(uri);



    }


    private void initTitle() {
        tvTopTile.setText("Fresco:圆形圆角");
    }




    @OnClick({R.id.bt_fresco3_1round,R.id.bt_fresco3_2round_angle})
    public  void itemOnclick(View view){
        switch (view.getId()) {
            case R.id.bt_fresco3_1round :
                //圆形剪裁
                RoundingParams params = RoundingParams.asCircle();
                params.setOverlayColor(Color.WHITE);//设置超出范围颜色
                hierarchy = builder.setRoundingParams(params).build();
                sdvFresco1Crop.setHierarchy(hierarchy);

                sdvFresco1Crop.setImageURI(uri);

                break;



            case R.id.bt_fresco3_2round_angle:
                //圆角剪裁

                params = RoundingParams.fromCornersRadius(50f);//设置圆角大小
                params.setOverlayColor(Color.WHITE);//设置覆盖层
                params.setBorder(Color.RED,6);
               // params.setRoundAsCircle(true);//如果是RoundingParams.fromCornersRadius, 这个可以强行进行圆角展示



                sdvFresco1Crop.getHierarchy().setRoundingParams(params);
               // sdvFresco1Crop.setImageURI(uri);

                break;



        }


    }
}
