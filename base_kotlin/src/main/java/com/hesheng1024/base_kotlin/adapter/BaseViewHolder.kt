package com.hesheng1024.base.base.adapter

import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author hesheng1024
 * @date 2020/2/8 11:20
 */
open class BaseViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    private var mViews: SparseArray<View> = SparseArray()

    fun <V : View> getView(@IdRes viewId: Int): V? {
        var view = mViews.get(viewId)
        if (view == null) {
            view = mItemView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as V?
    }
}