package com.example.chen.atguigucode.commom.countdownview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.countdownview.activity.RecyclerViewActivity;
import com.example.chen.atguigucode.commom.countdownview.activity.DynamicShowActivity;
import com.example.chen.atguigucode.commom.countdownview.activity.ListViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;


public class CountdownViewMainActivity extends Activity implements CountdownView.OnCountdownEndListener {

    private Context mContext;



    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.cv_countdownViewTest1)
    CountdownView cvCountdownViewTest1;
    @BindView(R.id.cv_countdownViewTest2)
    CountdownView cvCountdownViewTest2;
    @BindView(R.id.cv_countdownViewTest211)
    CountdownView cvCountdownViewTest211;
    @BindView(R.id.cv_countdownViewTest21)
    CountdownView cvCountdownViewTest21;
    @BindView(R.id.cv_countdownViewTest22)
    CountdownView cvCountdownViewTest22;
    @BindView(R.id.cv_countdownViewTest3)
    CountdownView cvCountdownViewTest3;
    @BindView(R.id.cv_countdownViewTest4)
    CountdownView cvCountdownViewTest4;
    @BindView(R.id.cv_convertDaysToHours)
    CountdownView cvConvertDaysToHours;
    @BindView(R.id.cv_countdownViewTest5)
    CountdownView cvCountdownViewTest5;
    @BindView(R.id.cv_countdownViewTest6)
    CountdownView cvCountdownViewTest6;
    @BindView(R.id.btn_toDynamicShowActivity)
    Button btnToDynamicShowActivity;
    @BindView(R.id.btn_toListViewActivity)
    Button btnToListViewActivity;
    @BindView(R.id.btn_toRecyclerViewActivity)
    Button btnToRecyclerViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_view_main);
        ButterKnife.bind(this);
        mContext = this;

        initTitle();

        initTime();

    }


    /**
     * 初始化计时器的时间
     */
    private void initTime() {


        long time1 = (long) 5 * 60 * 60 * 1000;
        cvCountdownViewTest1.setTag("test1");
        cvCountdownViewTest1.start(time1);

        long time2 = (long) 30 * 60 * 1000;
        cvCountdownViewTest2.setTag("test2");
        cvCountdownViewTest2.start(time2);

        long time211 = (long) 30 * 60 * 1000;
        cvCountdownViewTest211.setTag("test21");
        cvCountdownViewTest211.start(time211);

        long time21 = (long) 24 * 60 * 60 * 1000;
        cvCountdownViewTest21.setTag("test21");
        cvCountdownViewTest21.start(time21);

        long time22 = (long) 30 * 60 * 1000;
        cvCountdownViewTest22.setTag("test22");
        cvCountdownViewTest22.start(time22);

        long time3 = (long) 9 * 60 * 60 * 1000;
        cvCountdownViewTest3.start(time3);

        long time4 = (long) 150 * 24 * 60 * 60 * 1000;
        cvCountdownViewTest4.start(time4);

        // long timeConvertDaysToHours = (long) 150 * 24 * 60 * 60 * 1000;
        cvConvertDaysToHours.start(time4);


        long time6 = (long) 2 * 60 * 60 * 1000;
        cvCountdownViewTest6.start(time6);


        new AsyncTask<Void, Long, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                long time = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        time += 1000;
                        publishProgress(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onProgressUpdate(Long... values) {
                super.onProgressUpdate(values);
                cvCountdownViewTest5.updateShow(values[0]);
            }
        }.execute();

    }

    private void initTitle() {
        tvTopTile.setText("CountdownView");

    }


    /**
     * button 按钮的点击事件
     *
     * @param view
     */
    @OnClick({R.id.btn_toDynamicShowActivity, R.id.btn_toListViewActivity, R.id.btn_toRecyclerViewActivity})
    void btnOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toDynamicShowActivity:

                startActivity(new Intent(mContext, DynamicShowActivity.class));


                break;
            case R.id.btn_toListViewActivity:
                startActivity(new Intent(mContext, ListViewActivity.class));
                break;
            case R.id.btn_toRecyclerViewActivity:
                startActivity(new Intent(mContext, RecyclerViewActivity.class));
                break;


        }
    }


    @Override
    public void onEnd(CountdownView cv) {
        Object tag = cv.getTag();
        if (null != tag) {
            Log.i("wg", "tag = " + tag.toString());
        }
    }


}