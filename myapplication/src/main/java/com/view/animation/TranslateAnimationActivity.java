package com.view.animation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TranslateAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_animation);
        container3 = (LinearLayout) findViewById(R.id.linearLayout);
        list = new ArrayList<>();
    }
    LinearLayout container3 ;
    List<View> list ;
    public void showAnim(View view) {
        list.clear();
        int childCount = container3.getChildCount() ;
        for (int i = 0 ; i < childCount  ; i++) {
            View uv = container3.getChildAt(i);
            list.add(uv);
        }

        for (int i = 0 ; i < childCount/2 ; i++) {
            View uv = container3.getChildAt(i);
            View dv = container3.getChildAt(childCount - 1 - i);
            int y1 = uv.getTop();
            int y2 = dv.getTop();

            Animation translateAnimation = new TranslateAnimation(0, 0 , y1 , y2 - y1);
            translateAnimation.setDuration(300);
            translateAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
            uv.startAnimation(translateAnimation);

            Animation translateAnimation2 = new TranslateAnimation(0,0  ,-y1 ,y1 -y2 );
            translateAnimation2.setDuration(300);
            translateAnimation2.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
            dv.startAnimation(translateAnimation2);
        }
        handle.sendEmptyMessageDelayed(0 , 300);

    }
 Handler handle = new Handler() {
     @Override
     public void handleMessage(Message msg) {

         container3.removeAllViews();
         Collections.reverse(list);
         for (View v:list
                 ) {
             container3.addView(v);
         }
     }
 };
}
