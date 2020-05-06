package com.hesheng1024.base_java.utils;

import android.util.Log;

import com.hesheng1024.base_java.BuildConfig;


/**
 * @author hesheng1024
 * @date 2019/5/22 16:42
 */
public class LogUtil {
    private LogUtil() {

    }

    public static void v(String msg) {
        v(getTag(), msg);
    }

    public static void v(String msg, Throwable e) {
        v(getTag(), msg, e);
    }

    public static void d(String msg) {
        d(getTag(), msg);
    }

    public static void d(String msg, Throwable e) {
        d(getTag(), msg, e);
    }

    public static void i(String msg) {
        i(getTag(), msg);
    }

    public static void i(String msg, Throwable e) {
        i(getTag(), msg, e);
    }

    public static void w(String msg) {
        w(getTag(), msg);
    }

    public static void w(String msg, Throwable e) {
        w(getTag(), msg, e);
    }

    public static void e(String msg) {
        e(getTag(), msg);
    }

    public static void e(String msg, Throwable e) {
        e(getTag(), msg, e);
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg, e);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg, e);
        }
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg, e);
        }
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg, e);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg, e);
        }
    }

    /**
     * 获取调用者的类名、方法、行数
     * @return str
     */
    private static String getTag() {
        //Thread.currentThread().getStackTrace()[1]
        //StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        String fileName = stackTraceElement.getFileName();
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        int lineNum = stackTraceElement.getLineNumber();
        return className.substring(className.lastIndexOf('.') + 1) + ":" + lineNum;
    }

    /**
     * @return 当前的类名(simpleName)
     */
    public static String getClassName() {
        String result;
        StackTraceElement thisMethodStack = Thread.currentThread().getStackTrace()[2];
        result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf("");
        result = result.substring(lastIndex + 1);
        //剔除匿名内部类名
        int i = result.indexOf("$");
        return i == -1 ? result : result.substring(0, i);
    }
}
