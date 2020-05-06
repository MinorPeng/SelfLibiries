package com.hesheng1024.base_java.network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hesheng1024.base_java.R;
import com.hesheng1024.base_java.utils.LogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
    public void onSubscribe(Disposable d) {
        LogUtil.e("onSubscribe");
    }

    @Override
    public void onNext(T t) {
        LogUtil.e("onNext");
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("onError" + e.toString());
    }

    @Override
    public void onComplete() {
        LogUtil.e("onComplete");
    }

}
