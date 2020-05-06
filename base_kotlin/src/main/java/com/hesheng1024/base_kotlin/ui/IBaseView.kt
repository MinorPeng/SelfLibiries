package com.hesheng1024.base.base

import androidx.annotation.NonNull

/**
 *
 * @author hesheng1024
 * @date 2020/2/7 16:59
 */
interface IBaseView {

    fun toastMsg(@NonNull msg: String)

    fun finishActivity()

    fun noMore()
}