package com.hesheng1024.base.base

import androidx.annotation.NonNull

/**
 *
 * @author hesheng1024
 * @date 2020/2/7 17:01
 */
abstract class BasePresenter<out V : IBaseView, out M : IBaseModel>(@NonNull protected val mView: V) {
    protected val mModel = this.createModel()

    abstract fun createModel(): M
}