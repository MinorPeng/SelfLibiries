package com.hesheng1024.base.base

import android.R
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.hesheng1024.base.utils.toastShow

/**
 *
 * @author hesheng1024
 * @date 2020/2/7 17:05
 */
abstract class BaseFragment<out P : BasePresenter<IBaseView, IBaseModel>> : Fragment(), IBaseView {

    protected val mPresenter = this.createPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun toastMsg(@NonNull msg: String) {
        toastShow(context, msg)
    }

    override fun noMore() {}

    override fun finishActivity() {
        //不建议在Fragment中finish掉Activity
        activity?.finish()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun createPresenter(): P

    protected abstract fun initView(view: View)

    /**
     * 获取状态栏高度
     *
     * @return int statusHeight
     */
    protected fun getStatusBarHeight(): Int {
        var height = 0
        val resourceId =
            this.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0 && context != null) { //还可以通过反射获取
            height = context!!.resources.getDimensionPixelOffset(resourceId)
        }
        return height
    }

    protected fun getActionBarHeight(): Int {
        var actionBarHeight = 0
        val tv = TypedValue()
        if (context != null && context!!.theme.resolveAttribute(R.attr.actionBarSize, tv, true)) { //方法一
            actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
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
        return actionBarHeight
    }
}
