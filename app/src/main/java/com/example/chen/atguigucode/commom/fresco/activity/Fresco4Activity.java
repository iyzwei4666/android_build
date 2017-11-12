package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco4Activity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sdv_fresco1_crop)
    SimpleDraweeView sdvFresco1Crop;
    @BindView(R.id.bt_fresco4_image)
    Button btFresco4Image;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco4);
        ButterKnife.bind(this);

        tvTopTile.setText("Fresco:渐进式展示图片");


        uri = Uri.parse("http://img.taopic.com/uploads/allimg/140326/235113-1403260U22059.jpg");
      //  sdvFresco1Crop.setImageURI(uri);
    }

    @OnClick(R.id.bt_fresco4_image)
    void  itemOnClick(View view){

        //1. 初始化图片地址 uri

        //2. 创建图片请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();

        //3. 创建controller
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(sdvFresco1Crop.getController())
                .build();

        //4. 展示图片
       sdvFresco1Crop.setController(controller);

    }

}
