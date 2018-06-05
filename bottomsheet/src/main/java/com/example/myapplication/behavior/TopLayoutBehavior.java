package com.example.myapplication.behavior;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.example.myapplication.R;
import com.example.myapplication.ScreenUtil;

import java.lang.ref.WeakReference;


/**
 * created by czh on 2018-01-26
 */

public class TopLayoutBehavior extends CoordinatorLayout.Behavior<View>{


    WeakReference<View> mTopLayoutRef;

    public TopLayoutBehavior() {
    }

    public TopLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        View view=child.findViewById(R.id.topLayout);
        mTopLayoutRef=new WeakReference<View>(view);
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child,View dependency) {

        View view=mTopLayoutRef.get();
        int height=view.getHeight();
        int bottom=view.getBottom();
        int top=dependency.getTop();
        int offset = (int) (ScreenUtil.getScreenHeight((Activity) mTopLayoutRef.get().getContext()) * 0.5);

        if (top-offset<height ) {
//            Log.e("TopLayoutBehavior1",""+(top-offset-bottom));
//            ViewCompat.offsetTopAndBottom(view,(top-offset-bottom));
            return true;
        }

        if (top>height+offset && bottom<height){
//            Log.e("TopLayoutBehavior2",""+(height-bottom));
//            ViewCompat.offsetTopAndBottom(view,(height-bottom));
        }
        return super.onDependentViewChanged(parent,child,dependency);

    }


}
