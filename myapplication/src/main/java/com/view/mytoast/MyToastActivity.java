package com.view.mytoast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.mytoast.TastyToast;


public class MyToastActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_my_toast);
    }

    public void showSuccessToast(View view) {
        TastyToast.makeText(getApplicationContext(), "Download Successful !", TastyToast.LENGTH_LONG,
                TastyToast.SUCCESS);
    }

    public void showWarningToast(View view) {
//        TastyToast.makeText(getApplicationContext(), "Are you sure ?", TastyToast.LENGTH_LONG,
//                TastyToast.WARNING);
    }

    public void showErrorToast(View view) {
        TastyToast.makeText(getApplicationContext(), "Downloading failed ! Try again later ", TastyToast.LENGTH_LONG,
                TastyToast.ERROR);
    }

    public void showInfoToast(View view) {
//        TastyToast.makeText(getApplicationContext(), "Searching for username : 'Rahul' ", TastyToast.LENGTH_LONG,
//                TastyToast.INFO);
        LinearLayout container3 = (LinearLayout) findViewById(R.id.linearLayout);
        int childCount = container3.getChildCount() ;
        for (int i = 0; i < childCount/2; i++) {
            View uv = container3.getChildAt(i);
            View dv = container3.getChildAt(childCount - 1 - i);
            int y1 = uv.getTop();
            int y2 = dv.getTop();

            Animation translateAnimation = new TranslateAnimation(0, 0 , 0 , y2);
            translateAnimation.setDuration(300);
            translateAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
            uv.startAnimation(translateAnimation);

            Animation translateAnimation2 = new TranslateAnimation(0, 0 , y1 , 0 );
            translateAnimation2.setDuration(300);
            translateAnimation2.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
            dv.startAnimation(translateAnimation2);
        }
    }

    public void showDefaultToast(View view) {
        TastyToast.makeText(getApplicationContext(), "This is Default Toast", TastyToast.LENGTH_LONG,
                TastyToast.DEFAULT);
    }


    public void showConfusingToast(View view) {
        TastyToast.makeText(getApplicationContext(), "I don't Know !", TastyToast.LENGTH_LONG,
                TastyToast.CONFUSING);
    }
}
