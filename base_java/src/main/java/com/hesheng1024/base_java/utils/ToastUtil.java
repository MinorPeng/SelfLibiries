package com.hesheng1024.base_java.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.hesheng1024.base_java.ContextHolder;

/**
 * @author hesheng1024
 * @date 2019/5/22 16:38
 */
public class ToastUtil {
    private ToastUtil() {

    }

    public static void show(String msg) {
        show(ContextHolder.getContext(), msg);
    }

    public static void show(@StringRes int res) {
        show(ContextHolder.getContext(), res);
    }

    public static void showLong(String msg) {
        showLong(ContextHolder.getContext(), msg);
    }

    public static void showLong(@StringRes int res) {
        showLong(ContextHolder.getContext(), res);
    }

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, @StringRes int res) {
        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, @StringRes int res) {
        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
    }
}
