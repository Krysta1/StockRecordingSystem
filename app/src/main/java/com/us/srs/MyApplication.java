package com.us.srs;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.litesuits.orm.LiteOrm;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.us.srs.net.http.HttpEngine;

import retrofit2.Retrofit;

public class MyApplication extends Application {
    private Retrofit retrofit;
    public static boolean islogin = false;
    public static Context applicationContext;
    public static LiteOrm liteOrm;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        initHttp();
        initDb();
        Stetho.initializeWithDefaults(this);
    }

    private void initDb() {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "srs.db");
        }
        liteOrm.setDebugged(true);
    }
    private void initHttp() {
        retrofit = HttpEngine.getRetrofit();
    }
}
