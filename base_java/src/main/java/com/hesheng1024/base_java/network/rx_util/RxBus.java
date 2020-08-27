package com.hesheng1024.base_java.network.rx_util;

import androidx.annotation.NonNull;

import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;

/**
 * @author hesheng1024
 * @date 2020/5/29 10:03
 */
public class RxBus {

    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    private void demo() {

        RxBus.getInstance().toFlowable().subscribe(new FlowableSubscriber<Object>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static RxBus getInstance() {
        return Holder.INSTANCE;
    }

    public void post(@NonNull Object event) {
        if (hasSubscribers()) {
            mBus.onNext(event);
        }
    }

    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Flowable<Object> toFlowable() {
        return mBus;
    }

    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }

    private static class Holder {
        private static final RxBus INSTANCE = new RxBus();
    }
}
