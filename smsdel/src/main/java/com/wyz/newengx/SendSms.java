package com.wyz.newengx;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author idulc
 */
public class SendSms extends BroadcastReceiver {

    private static final String TAG = "SendSms";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
    /* android.content.BroadcastReceiver.getResultCode()方法 */
            switch (getResultCode()) {
                /* 发送短信成功 */
                case Activity.RESULT_OK:
                    context.startService(new Intent(context,  MyIntentService.class) );//启动倒计时服务
                    Log.d(TAG,  "发送短信成功");

                    break;
                /* 表示普通错误 */
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                /*表示无线广播被明确地关闭*/
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                /*表示没有提供pdu*/
                case SmsManager.RESULT_ERROR_NULL_PDU:

                default:
                    Toast.makeText(context,"发送短信失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}