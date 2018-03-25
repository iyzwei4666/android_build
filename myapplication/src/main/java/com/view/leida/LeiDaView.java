package com.view.leida;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by weiyongzhi on 2018/3/25.
 */

public class LeiDaView extends View {

    public LeiDaView(Context context) {
        super(context);
        init(context);
    }

    public LeiDaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LeiDaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            matrix.postRotate(degree++ , w /2 ,h/2);
            LeiDaView.this.invalidate();
            handler.postDelayed(runnable , 100);
        }
    };
    int degree = 0;
    private void init(Context context) {
        w = context.getResources().getDisplayMetrics().widthPixels;
        h = context.getResources().getDisplayMetrics().heightPixels;
        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        paint2 = new Paint();
        paint2.setStrokeWidth(4);
        paint2.setColor(Color.parseColor("#88888888"));
        paint2.setAntiAlias(true);


        Shader shader = new SweepGradient(w/2 , h/2 , Color.TRANSPARENT  ,Color.parseColor("#AAAAAAAA") );
        paint2.setShader(shader);

        matrix = new Matrix();
        handler.post(runnable);
    }

    int w;
    int h;
    Paint paint;
    Paint paint2;

    Matrix matrix;
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(w/2 ,h /2 ,1*h/2/4,paint);
        canvas.drawCircle(w/2 ,h /2 ,2*h/2/4,paint);
        canvas.drawCircle(w/2 ,h /2 ,3*h/2/4,paint);
        canvas.drawCircle(w/2 ,h /2 ,4*h/2/4,paint);


        canvas.concat(matrix);
        canvas.drawCircle(w/2 ,h /2 ,4*h/2/4,paint2);
        matrix.reset();

        super.onDraw(canvas);

    }
}
