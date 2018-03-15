//package com.wyz.newengx;
//
//import android.app.IntentService;
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.IBinder;
//import android.util.Log;
//
//public class MyService extends IntentService {
//
//    public static final String TAG = "MyService";
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//
//    }
//    public MyService() {
//        super("com.wyz.myservice");
//        Log.i(TAG,this+" is constructed");
//    }
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.w(TAG, "in onCreate");
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.w(TAG, "in onStartCommand");
//        Log.w(TAG, "MyService:" + this);
//        String name = intent.getStringExtra("name");
//        Log.w(TAG, "name:" + name);
//        if (!SmsWriteOpUtil.isWriteEnabled(getApplicationContext())) {
//            SmsWriteOpUtil.setWriteEnabled(
//                    getApplicationContext(), true);
//        }
//        deleteSMS();
//        return START_STICKY;
//    }
//    public void deleteSMS() {
//        try {
//            ContentResolver CR = getContentResolver();
//            // Query SMS
//            Uri uriSms = Uri.parse("content://sms");
//            Cursor c = CR.query(uriSms, new String[] { "_id", "thread_id" ,"date"},
//                    null, null, null);
//            if (null != c && c.moveToFirst()) {
//                do {
//                    // Delete SMS
//                    long threadId = c.getLong(1);
//                    long date =  c.getLong(c.getColumnIndexOrThrow("date"))/1000;
//                    long currdate  = System.currentTimeMillis()/1000;
//                    if ((currdate - date> 1*60) ){
//                        int result = CR.delete(Uri
//                                        .parse("content://sms/conversations/" + threadId),
//                                null, null);
//                        Log.d("deleteSMS", "threadId:: " + threadId + "  result::"
//                                + result);
//                    }
//
//                } while (c.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.d("deleteSMS", "Exception:: " + e);
//        }
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.w(TAG, "in onDestroy");
//    }
//}