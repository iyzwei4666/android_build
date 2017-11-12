package com.example.chen.atguigucode.commom.banner.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by chen on 2017/8/18.
 */

public class ScreenUtils {


    public static int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();

        return dm.heightPixels;
    }


    public static int getScreenW(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
       return dm.widthPixels;
    }


}
