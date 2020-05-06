package com.hesheng1024.base_java;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * @author hesheng1024
 * @date 2019/12/8 16:11:14
 */
public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        long startTime = System.currentTimeMillis();
        Log.e("startTime", String.valueOf(startTime));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.init(this, getBaseContext());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // Glide.get(this).onTrimMemory(level);
    }
}
