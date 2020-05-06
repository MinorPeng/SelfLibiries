package com.hesheng1024.base.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author hesheng1024
 * @date 2020/2/8 11:28
 */
abstract class BaseRecyclerAdapter<T>(dataList: List<T>? = null) : RecyclerView.Adapter<BaseViewHolder>() {

    private val mDataList: MutableList<T> = ArrayList()
    private var mListener: IBaseIClickListener? = null

    init {
        if (!dataList.isNullOrEmpty()) {
            this.addList(dataList)
        }
    }

    open fun setList(@NonNull data: List<T>) {
        mDataList.clear()
        addList(data)
    }

    open fun add(@NonNull data: T) {
        mDataList.add(data)
        notifyDataSetChanged()
    }

    open fun add(position: Int, @NonNull data: T) {
        mDataList.add(position, data)
        notifyDataSetChanged()
    }

    open fun addList(@NonNull data: List<T>) {
        mDataList.addAll(data)
        notifyDataSetChanged()
    }

    open fun remove(position: Int) {
        mDataList.removeAt(position)
        notifyDataSetChanged()
    }

    open fun remove(@NonNull data: T) {
        mDataList.remove(data)
        notifyDataSetChanged()
    }

    open fun clear() {
        mDataList.clear()
        notifyDataSetChanged()
    }

    @Nullable
    open fun get(position: Int): T {
        return mDataList[position]
    }

    open fun getList(): List<T> {
        return mDataList
    }

    open fun setClickListener(listener: IBaseIClickListener) {
        this.mListener = listener
    }

    protected fun getListener() = mListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(getItemLayoutId(), parent, false))
    }

    override fun getItemCount(): Int {
        return if (mDataList.isNullOrEmpty()) 0 else mDataList.size
    }

    /**
     * item layout_block_edit_text id
     * @return resId
     */
    @LayoutRes
    protected abstract fun getItemLayoutId(): Int
}