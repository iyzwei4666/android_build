package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco9Activity extends Activity {


    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sdv_fresco_crop)
    SimpleDraweeView sdvFrescoCrop;
    @BindView(R.id.bt_fresco9_edit)
    Button btFresco9Edit;
    @BindView(R.id.tv_fresco9_info)
    TextView tvFresco9Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco9);
        ButterKnife.bind(this);


        tvTopTile.setText("Fresco:编辑图片");


        //initData();

    }

    private void initData() {
        Uri uri = Uri.parse("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3613661563,573651156&fm=26&gp=0.jpg");
        sdvFrescoCrop.setImageURI(uri);

    }


    @OnClick(R.id.bt_fresco9_edit)
    void editImage() {

        Uri uri = Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1502964446&di=967300e765e4708e3449aa31b57ce322&src=http://pic41.nipic.com/20140523/18334583_142733371145_2.jpg");
        Postprocessor redMeshPostprocessor = new BasePostprocessor() {
            @Override
            public String getName() {
                return "redMeshPostprocessor";
            }

            @Override
            public void process(Bitmap bitmap) {
                for (int x = 0; x < bitmap.getWidth(); x += 2) {
                    for (int y = 0; y < bitmap.getHeight(); y += 2) {
                        bitmap.setPixel(x, y, Color.RED);
                    }
                }
            }
        };

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setPostprocessor(redMeshPostprocessor)
                .build();

        PipelineDraweeController controller = (PipelineDraweeController)
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(sdvFrescoCrop.getController())
                        // other setters as you need
                        .build();
        sdvFrescoCrop.setController(controller);
    }


}
