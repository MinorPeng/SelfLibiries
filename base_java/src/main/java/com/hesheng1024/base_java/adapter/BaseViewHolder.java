package com.hesheng1024.base_java.adapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


/**
 * @author hesheng1024
 * @date 2019/5/29 17:50
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final View mItemView;
    private final SparseArray<View> mViews;
    private final HolderHelper mHolderHelper;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mItemView = itemView;
        mViews = new SparseArray<>();
        mHolderHelper = new HolderHelper();
    }

    /**
     * get child view in itemView
     *
     * @param viewId res id
     * @return view or null
     */
    @Nullable
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public HolderHelper getHolderHelper() {
        return mHolderHelper;
    }

    /**
     * help to load data with usual API
     */
    class HolderHelper {

        public HolderHelper setText(@IdRes int resId, String s) {
            final TextView tv = getView(resId);
            if (tv != null) {
                tv.setText(s);
            }
            return this;
        }

        public HolderHelper setImage(@IdRes int resId, String url) {
            final ImageView iv = getView(resId);
            if (iv != null) {
                Glide.with(iv.getContext()).load(url).into(iv);
            }
            return this;
        }

        public HolderHelper setImage(@IdRes int resId, @DrawableRes int drawableId) {
            final ImageView iv = getView(resId);
            if (iv != null) {
                Glide.with(iv.getContext()).load(drawableId).into(iv);
            }
            return this;
        }
    }
}
