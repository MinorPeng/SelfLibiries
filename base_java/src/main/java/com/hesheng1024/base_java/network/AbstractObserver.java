package com.hesheng1024.base_java.network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hesheng1024.base_java.R;
import com.hesheng1024.base_java.utils.LogUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @author hesheng1024
 * @date 2019/6/22 15:08
 */
public abstract class AbstractObserver<T> implements Observer<T> {

    public AbstractObserver() {
        this(null, null);
    }

    public AbstractObserver(@Nullable Context context) {
        this(context, null);
    }

    public AbstractObserver(@Nullable Context context, @Nullable String hint) {
        if (context != null) {
            initProgress(context, hint);
        }
    }

    private void initProgress(Context context, @Nullable String hint) {
        final View progressDialog = LayoutInflater.from(context).inflate(R.layout.layout_progressbar, null);
        final PopupWindow popupWindow = new PopupWindow(progressDialog);
        popupWindow.setOutsideTouchable(false);
        final TextView tvMsg = progressDialog.findViewById(R.id.tv_layout_progressbar_msg);
        if (hint != null) {
            tvMsg.setText(hint);
        }
        final TextView tvCancel = progressDialog.findViewById(R.id.tv_layout_progressbar_cancel);
        tvCancel.setOnClickListener(v -> popupWindow.dismiss());
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        LogUtil.i("onSubscribe");
    }

    @Override
    public void onNext(T t) {
        LogUtil.i("onNext");
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("onError" + e.toString());
    }

    @Override
    public void onComplete() {
        LogUtil.i("onComplete");
    }

}
