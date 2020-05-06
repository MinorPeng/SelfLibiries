package com.hesheng1024.base_java;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * @author hesheng1024
 * @date 2020/5/6 18:45
 */
public final class ContextHolder {

    private ContextHolder() {
    }

    private static Application mApplication;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    static void init(@NonNull Application application, @NonNull Context context) {
        mApplication = application;
        mContext = context;
    }

    public static Application getApplication() {
        return mApplication;
    }

    public static Context getContext() {
        return mContext;
    }
}
