package com.github.tonytanganadroid.glideencyptiondemo;

import android.app.Application;

import com.facebook.soloader.SoLoader;

import hugo.weaving.DebugLog;

@DebugLog
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }
}
