package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco8Activity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sdv_fresco_crop)
    SimpleDraweeView sdvFrescoCrop;
    @BindView(R.id.bt_fresco8_scale)
    Button btFresco8Scale;
    @BindView(R.id.bt_fresco8_rot)
    Button btFresco8Rot;
    @BindView(R.id.tv_fresco8_info)
    TextView tvFresco8Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco8);
        ButterKnife.bind(this);

        tvTopTile.setText("Fresco:压缩和旋转");

        innitData();


    }

    private void innitData() {

        Uri uri = Uri.parse("http://img2.niutuku.com/desk/1208/1450/ntk-1450-9891.jpg");
        sdvFrescoCrop.setImageURI(uri);

    }


    @OnClick({R.id.bt_fresco8_scale,R.id.bt_fresco8_rot})
    void itemOnclick(View view){
        String desc = "";

            Uri uri = Uri.parse("http://img2.niutuku.com/desk/1208/1450/ntk-1450-9891.jpg");
        if(view.getId() ==R.id.bt_fresco8_scale) {
            desc = "Resize 并不改变原始图片，它只在解码前修改内存中的图片大小。";
            tvFresco8Info.setText(desc);


            int width = 50, height = 50;
            //1. 创建request
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setResizeOptions(new ResizeOptions(width, height))
                    .build();


            //2. 创建controller
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setOldController(sdvFrescoCrop.getController())
                    .setImageRequest(request)
                    .build();
            sdvFrescoCrop.setController(controller);




        }else if(view.getId() ==R.id.bt_fresco8_rot) {


            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setAutoRotateEnabled(true)
                    .build();
            DraweeController controller  = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .build();
            sdvFrescoCrop.setController(controller);

        }


    }



}
