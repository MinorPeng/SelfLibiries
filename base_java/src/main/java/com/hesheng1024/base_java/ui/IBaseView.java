package com.hesheng1024.base_java.ui;

import androidx.annotation.NonNull;

/**
 * @author hesheng1024
 * @date 2019/5/22 15:10
 */
public interface IBaseView {

    /**
     * toast
     *
     * @param msg String
     */
    void toastMsg(@NonNull String msg);

    /**
     * finish
     */
    void finishActivity();

    /**
     * no more data
     */
    void noMore();
}
