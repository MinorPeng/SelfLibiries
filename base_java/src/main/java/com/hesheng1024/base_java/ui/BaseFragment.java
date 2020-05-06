package com.hesheng1024.base_java.ui;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hesheng1024.base_java.utils.ToastUtil;

import java.util.Objects;

/**
 * @author hesheng1024
 * @date 2019/5/26 18:02
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    protected final P mPresenter = createPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void toastMsg(@NonNull String msg) {
        ToastUtil.show(getContext(), msg);
    }

    @Override
    public void noMore() {

    }

    @Override
    public void finishActivity() {
        //不建议在Fragment中finish掉Activity
        Objects.requireNonNull(getActivity()).finish();
    }

    /**
     * get layout id
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * init view
     *
     * @param view rootView
     */
    protected abstract void initView(@NonNull View view);

    /**
     * create presenter
     *
     * @return BasePresenter
     */
    protected abstract P createPresenter();

    /**
     * 获取状态栏高度
     *
     * @return int statusHeight
     */
    protected int getStatusBarHeight() {
        int height = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0 && getContext() != null) {
            //还可以通过反射获取
            height = getContext().getResources().getDimensionPixelOffset(resourceId);
        }
        return height;
    }

    protected int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getContext() != null && getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            //方法一
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());

            //方法二
            //int[] attribute = new int[] { android.R.attr.actionBarSize };
            //TypedArray array = context.obtainStyledAttributes(tv.resourceId, attribute);
            //int actionBarHeight1 = array.getDimensionPixelSize(0 /* index */, -1 /* default size */);
            //array.recycle();

            //方法三
            //TypedArray actionbarSizeTypedArray = context.obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
            //float actionBarHeight2 = actionbarSizeTypedArray.getDimension(0, 0);
            //actionbarSizeTypedArray.recycle();
        }
        return actionBarHeight;
    }

}
