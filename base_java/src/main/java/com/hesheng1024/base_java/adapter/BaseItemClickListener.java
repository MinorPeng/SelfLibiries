package com.hesheng1024.base_java.adapter;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * @author hesheng1024
 * @date 2019/5/29 18:08
 */
public interface BaseItemClickListener {

    /**
     * click
     *
     * @param view:
     * @param position:
     */
    void onClick(@NonNull View view, int position);

    /**
     * long click
     *
     * @param view:
     * @param position:
     * @return click
     */
    boolean onLongClick(@NonNull View view, int position);
}
