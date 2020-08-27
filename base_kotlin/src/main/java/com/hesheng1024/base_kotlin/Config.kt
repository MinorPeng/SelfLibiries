package com.hesheng1024.base_kotlin

import com.hesheng1024.base.utils.ContextHolder

/**
 *
 * @author hesheng1024
 * @date 2020/6/18 10:18
 */

fun getFilePath(): String {
    return if (ContextHolder.getMainContext().externalCacheDir != null)
        ContextHolder.getMainContext().externalCacheDir!!.absolutePath
    else ContextHolder.getMainContext().cacheDir.absolutePath
}