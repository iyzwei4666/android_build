package com.example.chen.atguigucode.utils;

import android.content.Context;

/**
 * Created by chen on 2017/8/15.
 */

public class Utils {


    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Context context,float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);//四舍五入

    }


}
