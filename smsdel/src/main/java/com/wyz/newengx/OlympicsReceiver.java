package com.wyz.newengx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OlympicsReceiver extends BroadcastReceiver
{   
    public void onReceive(Context context, Intent intent)
    {   
             context.startService(new Intent(context,  MyIntentService.class) );//启动倒计时服务
             Toast.makeText(context, "OlympicsReminder service has started!", Toast.LENGTH_LONG).show();
            //这边可以添加开机自动启动的应用程序代码   
    }
}