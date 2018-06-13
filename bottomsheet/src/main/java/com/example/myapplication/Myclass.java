package com.example.myapplication;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by WYZ on 2018/6/13.
 */

public class Myclass extends NestedScrollView {
    public Myclass(Context context) {
        super(context);
    }

    public Myclass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Myclass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (100 > t) {
        Log.e("onScrollChanged", "onScrollChanged" + t);
        return;
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
