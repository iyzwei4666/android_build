package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco10Activity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_fresco10_add)
    Button btFresco10Add;
    @BindView(R.id.ll_fresco10_show)
    LinearLayout llFresco10Show;
    @BindView(R.id.tv_fresco10_info)
    TextView tvFresco10Info;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco10);
        ButterKnife.bind(this);

        this.mContext = this;

        //initTitle
        tvTopTile.setText("Fresco1");

    }

    @OnClick(R.id.bt_fresco10_add)
    void itemOnclick(View view) {

        Uri uri = Uri.parse("http://img0.imgtn.bdimg.com/it/u=2516532654,4074182482&fm=26&gp=0.jpg");

        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);
        simpleDraweeView.setAspectRatio(1.0f);//设置宽高比


        //simpleDraweeView.setImageURI(uri);


        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();

        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);

        llFresco10Show.addView(simpleDraweeView);

        tvFresco10Info.setText("add image");
    }


}
