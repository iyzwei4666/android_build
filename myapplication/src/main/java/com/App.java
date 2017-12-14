package com;

import android.app.Application;

import com.example.myapplication.BuildConfig;
import com.frame.greendao.DaoMaster;
import com.frame.greendao.DaoSession;
import com.frame.objectbox.MyObjectBox;

import org.greenrobot.greendao.database.Database;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;


public class App extends Application {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */


    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        createSession();
        createBox();
    }

    private void createSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "apps-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void createBox() {
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(this);
        }
    }
    private BoxStore boxStore;
    public BoxStore getBoxStore() {
        return boxStore;
    }


}
