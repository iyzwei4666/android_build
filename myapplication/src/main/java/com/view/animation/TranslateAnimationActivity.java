package com.view.animation;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.mytoast.TastyToast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TranslateAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_animation);
        layoutContainer = (LinearLayout) findViewById(R.id.linearLayout);

    }

    private LinearLayout layoutContainer;
    public void showAnim(View view) {
        final List<View>  list = new ArrayList<>();
        int childCount = layoutContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View uv = layoutContainer.getChildAt(i);
            uv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TastyToast.makeText(getApplicationContext(), "Download Successful !"+v.getId(), TastyToast.LENGTH_LONG,  TastyToast.SUCCESS);
                }
            });
            list.add(uv);
        }

        for (int i = 0; i < childCount / 2; i++) {
            final View uv = layoutContainer.getChildAt(i);
            final View dv = layoutContainer.getChildAt(childCount - 1 - i);
            int y1 = uv.getTop();
            int y2 = dv.getTop();

            Animation uvanim = new TranslateAnimation(0, 0, y1, y2 - y1);
            uvanim.setDuration(300);
//            translateAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
            uv.startAnimation(uvanim);
            uvanim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    int left = uv.getLeft() ;
                    int top = uv.getTop();
                    int width = uv.getWidth();
                    int height = uv.getHeight();
                    uv.clearAnimation();
                    uv.layout(left, top, left+width, top+height);
                }
            });


            Animation dvanim = new TranslateAnimation(0, 0, -y1, y1 - y2);
            dvanim.setDuration(300);
//            translateAnimation2.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
            dv.startAnimation(dvanim);

            dvanim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    int left = dv.getLeft() ;
                    int top = dv.getTop();
                    int width = dv.getWidth();
                    int height = dv.getHeight();
                    dv.clearAnimation();
                    dv.layout(left, top, left+width, top+height);
                }
            });
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutContainer.removeAllViews();
                Collections.reverse(list);
                for (View v : list  ) {
                    layoutContainer.addView(v);
                }
            }
        }, 300 );
    }

}
