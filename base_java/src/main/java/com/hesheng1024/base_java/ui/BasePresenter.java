package com.hesheng1024.base_java.ui;

import androidx.annotation.NonNull;

/**
 * @author hesheng1024
 * @date 2019/5/22 17:04
 */
public abstract class BasePresenter<V extends IBaseView, M extends IBaseModel> {
    protected final V mView;
    protected final M mModel;

    public BasePresenter(@NonNull V view) {
        this.mView = view;
        mModel = createModel();
    }

    /**
     * create model
     *
     * @return model
     */
    protected abstract M createModel();
}
