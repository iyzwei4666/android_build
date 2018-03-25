package com.wyz.newengx;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class MyIntentService extends IntentService {
    final static String TAG = "com.wyz.MyIntentService";

    public MyIntentService() {
        super("com.wyz.myservice");
        Log.i(TAG, this + " is constructed");
    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        Log.i(TAG, "begin onHandleIntent() in " + this);
        try {
            if (!SmsWriteOpUtil.isWriteEnabled(getApplicationContext())) {
                SmsWriteOpUtil.setWriteEnabled(
                        getApplicationContext(), true);
            }
//                if (Environment.MEDIA_MOUNTED.equals(Environment .getExternalStorageState())) {
//                    File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
//                    FileOutputStream fos = new FileOutputStream(file);
//                    fos.write((new Date() + "我启动了").getBytes());
//                    fos.flush();
//                    fos.close();
//                }
            deleteSMS();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "end onHandleIntent() in " + this);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, this + " is destroy");
    }

    public void deleteSMS() {
        try {

            ContentResolver CR = getContentResolver();
            // Query SMS
            Uri uriSms = Uri.parse("content://sms");

            Cursor c = CR.query(uriSms, new String[] { "_id", "thread_id" ,"date"},
                    null, null, null);

            if (null != c && c.moveToFirst()) {
                do {
                    // Delete SMS
                    long threadId = c.getLong(1);
                    long date =  c.getLong(c.getColumnIndexOrThrow("date"))/1000;
                    long currdate  = System.currentTimeMillis()/1000;
                    if ((currdate - date> 7*24*60*60) ){
                        int result = CR.delete(Uri
                                        .parse("content://sms/conversations/" + threadId),
                                null, null);
                        Log.d("deleteSMS", "threadId:: " + threadId + "  result::"
                                + result);
                    }

                } while (c.moveToNext());
            }
            Uri uriMms = Uri.parse("content://mms");
            Cursor mc = CR.query(uriMms, new String[] { "_id", "thread_id" ,"date"},
                    null, null, null);
            getContentResolver().delete(uriMms , null, null);
            if (null != mc && mc.moveToFirst()) {
                do {
                    // Delete SMS
                    long threadId = mc.getLong(1);
                    long date =  mc.getLong(mc.getColumnIndexOrThrow("date"))/1000;
                    long currdate  = System.currentTimeMillis()/1000;
                    if ((currdate - date> 7*24*60*60) ){
                        int result = CR.delete(Uri
                                        .parse("content://mms/conversations/" + threadId),
                                null, null);
                        Log.d("deleteMMS", "threadId:: " + threadId + "  result::"
                                + result);
                    }

                } while (mc.moveToNext());
            }
        } catch (Exception e) {
            Log.d("deleteSMS", "Exception:: " + e);
        }
    }
}