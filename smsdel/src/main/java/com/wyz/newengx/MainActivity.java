package com.wyz.newengx;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity .this,  MyIntentService.class) );//
            }
        });


//        if (!SmsWriteOpUtil.isWriteEnabled(getApplicationContext())) {
//            SmsWriteOpUtil.setWriteEnabled(
//                    getApplicationContext(), true);
//        }
//        deleteSMS();

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
                    if ((currdate - date> 1*60) ){
                        int result = CR.delete(Uri
                                        .parse("content://sms/conversations/" + threadId),
                                null, null);
                        Log.d("deleteSMS", "threadId:: " + threadId + "  result::"
                                + result);
                    }

                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.d("deleteSMS", "Exception:: " + e);
        }
    }
    public void deleteSMS1() {
        try {
            ContentResolver CR = getContentResolver();
            // Query SMS
//            Uri uriSms = Uri.parse("content://sms/inbox");
//            Uri uriSms = Uri.parse("content://sms/sent");
            Uri uriSms = Uri.parse("content://sms");
            Cursor c = CR.query(uriSms, new String[] { "_id", "thread_id","date","body","address" },null, null, null);
            if (null != c && c.moveToFirst()) {
                do {
                    // Delete SMS
                    long threadId = c.getLong(0);
//                        for (int i = 0; i < c.getColumnCount() ; i++){
//                            String name  = c.getColumnName(i) ;
                            long date =  c.getLong(c.getColumnIndexOrThrow("date"))/1000;
                             long currdate  = System.currentTimeMillis()/1000;
                            if ((currdate - date> 1*60) ){
                                            int result = CR.delete(Uri.parse("content://sms/conversations/" + threadId), null, null);
                                            Log.d("deleteSMS", "threadId:: " + threadId + "  result::" + result);

                    }
//                            System.out.println(name + "----------------"+subject);
//                        }
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.d("deleteSMS", "Exception:: " + e);
        }
    }



}
