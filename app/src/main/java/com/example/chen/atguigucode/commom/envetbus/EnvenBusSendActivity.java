package com.example.chen.atguigucode.commom.envetbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.envetbus.event.MessageEvent;
import com.example.chen.atguigucode.commom.envetbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnvenBusSendActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_evenbs_main_send)
    Button btEvenbsMainSend;
    @BindView(R.id.bt_evenbs_recieve)
    Button btEvenbsRecieve;
    @BindView(R.id.tv_evenbs_info)
    TextView tvEvenbsInfo;

    private  boolean isRegistered = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enven_bus_send);
        ButterKnife.bind(this);

        tvTopTile.setText("EventBus 发送数据页面");


    }


    @OnClick({R.id.bt_evenbs_main_send,R.id.bt_evenbs_recieve})
    public  void itemOnclick(View view){
        switch (view.getId()) {
            case R.id.bt_evenbs_main_send ://主线程发送数据
                sendMessageToFirstPage();
                break;

            case R.id.bt_evenbs_recieve://接受粘性数据
                recieveStikyEvent();
                break;
        }
    }


    /**
     * 二: 点击界面按钮进行注册,并接受 粘性事件
     */
    private void recieveStikyEvent() {

        if(!isRegistered) {
            EventBus.getDefault().register(EnvenBusSendActivity.this);//注册 粘性事件

            isRegistered = true;
        }

    }

    /**
     * 接受粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onStikyEvent(StickyEvent event) {
        if(event!=null) {
            tvEvenbsInfo.setText(event.msg);
        }


    }

    /**
     * 在主线程,向第一个page 发送数据
     */
    private void sendMessageToFirstPage() {

        MessageEvent event = new MessageEvent("atuguigu", "123234");
        tvEvenbsInfo.setText("发送:"+event.toString());

        EventBus.getDefault().post(event);
    }

    @Override
    protected void onDestroy() {
        if(isRegistered) {
            EventBus.getDefault().removeAllStickyEvents();
            EventBus.getDefault().unregister(this);
            isRegistered = false;
        }


        super.onDestroy();
    }
}
