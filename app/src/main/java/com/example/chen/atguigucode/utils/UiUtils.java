package com.example.chen.atguigucode.utils;

import android.content.Context;

/**
 * Created by chen on 2017/8/12.
 */

public  class UiUtils {


    //与屏幕分辨率相关的
    public static int dp2px(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);//四舍五入

    }

    public static int px2dp(int px, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);//四舍五入
    }





}
