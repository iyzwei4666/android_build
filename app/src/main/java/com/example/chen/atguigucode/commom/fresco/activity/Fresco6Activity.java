package com.example.chen.atguigucode.commom.fresco.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco6Activity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sdv_fresco_crop)
    SimpleDraweeView sdvFrescoCrop;
    @BindView(R.id.bt_fresco6_bt1)
    Button btFresco6Bt1;
    @BindView(R.id.bt_fresco6_local)
    Button btFresco6Local;
    @BindView(R.id.bt_fresco6_reuse)
    Button btFresco6Reuse;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco6);
        ButterKnife.bind(this);


        //title
        tvTopTile.setText("Fresco:多图请求及图片复用");

        initData();
    }

    private void initData() {

        uri = Uri.parse("http://old.bz55.com/uploads/allimg/140624/1-140624153431.jpg");
        sdvFrescoCrop.setImageURI(uri);
    }

    @OnClick({R.id.bt_fresco6_bt1, R.id.bt_fresco6_local, R.id.bt_fresco6_reuse})
    void itemOnclick(View view) {
        switch (view.getId()) {
            case R.id.bt_fresco6_bt1://多请求显示图片: 先显示一个缩略图,在显示原图
                Uri lowUri = Uri.parse("http://img0.imgtn.bdimg.com/it/u=2516532654,4074182482&fm=26&gp=0.jpg");
                Uri hightUri = Uri.parse("http://img2.niutuku.com/desk/1208/1450/ntk-1450-9891.jpg");


                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setLowResImageRequest(ImageRequest.fromUri(lowUri))
                        .setImageRequest(ImageRequest.fromUri(hightUri))
                        .build();
                sdvFrescoCrop.setController(controller);
                break;

            case R.id.bt_fresco6_local: //加载本地缩略图
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/meinv1.jpg";

                Log.d("Fresco6Activity", filePath);
                Uri uri = Uri.fromFile(new File(filePath));
//
//                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
//                        .setLocalThumbnailPreviewsEnabled(true)
//                        .build();
//
//                controller = Fresco.newDraweeControllerBuilder()
//                        .setImageRequest(request)
//                        .setOldController(sdvFrescoCrop.getController())
//                        .build();
//                sdvFrescoCrop.setController(controller);

                sdvFrescoCrop.setImageURI(uri);
                Toast.makeText(this, "toas", Toast.LENGTH_SHORT).show();
        }


    }


}
