package com.hesheng1024.base_java.network.rx_util;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author hesheng1024
 * @date 2019/5/22 16:24
 */
public class SchedulerTransformer {
    private SchedulerTransformer() { }

    public static <T> ObservableTransformer<T, T> transformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
