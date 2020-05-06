package com.hesheng1024.base_java.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hesheng1024
 * @date 2019/5/29 17:49
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<T> mDataList = new ArrayList<>();
    private BaseItemClickListener mListener = null;

    public BaseRecyclerAdapter() {
    }

    public BaseRecyclerAdapter(@NonNull List<T> datas) {
        addList(datas);
    }

    public void add(@NonNull T data) {
        mDataList.add(data);
        notifyDataSetChanged();
    }

    public void add(int position, @NonNull T data) {
        mDataList.add(position, data);
        notifyDataSetChanged();
    }

    public void addList(@NonNull List<T> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDataList.remove(position);
        notifyDataSetChanged();
    }

    public void remove(@NonNull T data) {
        mDataList.remove(data);
        notifyDataSetChanged();
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    @Nullable
    public T get(int position) {
        return mDataList.get(position);
    }


    public void setClickListener(BaseItemClickListener listener) {
        this.mListener = listener;
    }

    @Nullable
    protected BaseItemClickListener getListener() {
        return mListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(getItemLayoutId(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bindHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDataList.isEmpty() ? 0 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * item layout id
     * @return resId
     */
    @LayoutRes
    protected abstract int getItemLayoutId();

    /**
     * 绑定ViewHolder
     *
     * @param holder: view holder
     * @param position: position in adapter
     */
    protected abstract void bindHolder(BaseViewHolder holder, int position);
}
