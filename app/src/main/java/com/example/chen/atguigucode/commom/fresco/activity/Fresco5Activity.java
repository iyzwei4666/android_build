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
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fresco5Activity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.sdv_fresco1_crop)
    SimpleDraweeView sdvFresco1Crop;
    @BindView(R.id.bt_fresco5_get_gif)
    Button btFresco5GetGif;
    @BindView(R.id.bt_fresco5_gif_start)
    Button btFresco5GifStart;
    @BindView(R.id.bt_fresco5_gif_stop)
    Button btFresco5GifStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco5);
        ButterKnife.bind(this);

        tvTopTile.setText("Fresco:动画");


    }

    @OnClick({R.id.bt_fresco5_get_gif,R.id.bt_fresco5_gif_start,R.id.bt_fresco5_gif_stop})
    void itemOnclick(View view){
        switch (view.getId()) {
            case R.id.bt_fresco5_get_gif :
                laodGif();

                break;
            case R.id.bt_fresco5_gif_start :
                startGif();


                break;
            case R.id.bt_fresco5_gif_stop :
                stopGif();

                break;
        }
    }

    private void startGif() {

        Animatable am = sdvFresco1Crop.getController().getAnimatable();
        if(am!=null) {
            am.start();
        }

    }

    private void stopGif() {
         Animatable am = sdvFresco1Crop.getController().getAnimatable();
        if(am!=null) {
            am.stop();
        }
    }

    private void laodGif() {
        Uri uri = Uri.parse("http://cdn.uehtml.com/201402/1392662622974.gif");
        sdvFresco1Crop.setImageURI(uri);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)//动画自动播放
                .build();
        sdvFresco1Crop.setController(controller);
    }


}
