package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco7Activity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sdv_fresco_crop)
    SimpleDraweeView sdvFrescoCrop;
    @BindView(R.id.bt_fresco7_load)
    Button btFresco7Load;
    @BindView(R.id.tv_fresco7_info)
    TextView tvFresco7Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco7);
        ButterKnife.bind(this);

        tvTopTile.setText("Fresco:图片加载监听");

    }


    @OnClick({R.id.bt_fresco7_load})
    void itemOnclick(View view){//监听片

//        普通加载
        Uri uri = Uri.parse("http://img0.imgtn.bdimg.com/it/u=2516532654,4074182482&fm=26&gp=0.jpg");
        //Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory()+"/Download/meinv1.jpg"));


//        sdvFrescoCrop.setImageURI(uri);

        //二 设置监听


        ControllerListener listener = new BaseControllerListener<ImageInfo>(){


            //加载图片完成
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {

                tvFresco7Info.setText("\n onFinalImageSet");
                if (imageInfo == null) {
                    return;
                }

                QualityInfo qualityInfo = imageInfo.getQualityInfo();

                tvFresco7Info.append("Final image received! " +
                        "\nSize: " + imageInfo.getWidth()
                        + "x" + imageInfo.getHeight()
                        + "\nQuality level: " + qualityInfo.getQuality()
                        + "\ngood enough: " + qualityInfo.isOfGoodEnoughQuality()
                        + "\nfull quality: " + qualityInfo.isOfFullQuality());
            }


            //渐进式加载图片回调
            @Override
            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                tvFresco7Info.append("\n onIntermediateImageSet");
            }

            //加载图片失败
            @Override
            public void onFailure(String id, Throwable throwable) {
                tvFresco7Info.append("\n onFailure:"+throwable.getMessage()+"id:"+id);

            }
        };

        //渐进式加载

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();



        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setControllerListener(listener)
                .setUri(uri)
                .build();

        sdvFrescoCrop.setController(controller);



    }
}
