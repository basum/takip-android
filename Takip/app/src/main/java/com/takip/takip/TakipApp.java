package com.takip.takip;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by bas on 12.03.19.
 */

public class TakipApp extends Application {
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
