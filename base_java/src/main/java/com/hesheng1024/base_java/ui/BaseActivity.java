package com.hesheng1024.base_java.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hesheng1024.base_java.R;
import com.hesheng1024.base_java.utils.LogUtil;
import com.hesheng1024.base_java.utils.ToastUtil;

/**
 * @author hesheng1024
 * @date 2019/5/22 15:09
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected final P mPresenter = createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isTranslucent()) {
            setTranslucent();
        }
        setContentView(inflateContentView());
        initView();
    }

    protected View inflateContentView() {
        return View.inflate(this, getLayoutId(), null);
    }

    @Override
    public void toastMsg(@NonNull String msg) {
        ToastUtil.show(this, msg);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void noMore() { }

    /**
     * 获取布局id
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 创建presenter
     *
     * @return presenter
     */
    protected abstract P createPresenter();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 获取状态栏高度
     *
     * @return int statusHeight
     */
    protected int getStatusBarHeight() {
        int statusHeight = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //还可以通过反射获取
            statusHeight = this.getResources().getDimensionPixelOffset(resourceId);
        }
        //return height;

        //int statusHeight = -1;
        //try {
        //    Class<?> clazz = Class.forName("com.android.internal.R$dimen");
        //    Object object = clazz.newInstance();
        //    int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
        //    statusHeight = getResources().getDimensionPixelSize(height);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        LogUtil.d("statusH:" + statusHeight);
        //不知道为什么，获取的高度是实际的高度的2倍 视觉上是这样
        return statusHeight / 2;
    }

    protected float getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            //方法一
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
           //方法二
           //int[] attribute = new int[] { android.R.attr.actionBarSize };
           //TypedArray array = obtainStyledAttributes(tv.resourceId, attribute);
           //int actionBarHeight1 = array.getDimensionPixelSize(0 /* index */, -1 /* default size */);
           //array.recycle();
           //
           //方法三
           //TypedArray actionbarSizeTypedArray = obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
           //float actionBarHeight2 = actionbarSizeTypedArray.getDimension(0, 0);
           //actionbarSizeTypedArray.recycle();
        }
        return actionBarHeight;
    }

    /**
     * 是否透明状态栏 默认否
     *
     * @return boolean
     */
    protected boolean isTranslucent() {
        return false;
    }

    protected int getStatusBarBackground() {
        return R.drawable.bg_toolbar;
    }

    private void setTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
