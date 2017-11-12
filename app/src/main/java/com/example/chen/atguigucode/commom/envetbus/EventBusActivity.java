package com.example.chen.atguigucode.commom.envetbus;

import android.app.Activity;
import android.content.Intent;
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

/**
 * Eventbus
 * 1. 注册广播/解注册广播
 * 2. 构造发消息类
 * 3. 发送消息
 * 4. 接受消息
 */
public class EventBusActivity extends Activity {


    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_eventbus_to_sendpage)
    Button btEventbusToSendpage;
    @BindView(R.id.bt_eventbuse_nianxing)
    Button btEventbuseNianxing;
    @BindView(R.id.tv_eventbus_info)
    TextView tvEventbusInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);

        //title
        tvTopTile.setText("EventBus");

        //1. 注册eventbus
        EventBus.getDefault().register(this);


    }


    @OnClick({R.id.bt_eventbus_to_sendpage, R.id.bt_eventbuse_nianxing})
    public  void  itemOnclick(View view){
    switch (view.getId()){
        case R.id.bt_eventbus_to_sendpage :
            Intent intent = new Intent(EventBusActivity.this,EnvenBusSendActivity.class);
            startActivity(intent);

            break;
        case R.id.bt_eventbuse_nianxing:
            sendNianxingEvent();
            break;
    }


    }

    /**
     * 发送粘性事件
     */
    private void sendNianxingEvent() {

        StickyEvent event = new StickyEvent("this is sticky event message");
        tvEventbusInfo.setText("发送粘性事件:"+event.msg);

        EventBus.getDefault().postSticky(event);

        Intent intent = new Intent(EventBusActivity.this,EnvenBusSendActivity.class);
        startActivity(intent);

    }


    /**
     * 接受消息的回调
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        String name = event.name;
        String password = event.password;


        tvEventbusInfo.setText("name:"+name+"\n"+"password:"+password);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
