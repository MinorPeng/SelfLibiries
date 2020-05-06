package com.hesheng1024.base_java.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.hesheng1024.base_java.R;
import com.hesheng1024.base_java.utils.LogUtil;

import java.util.Objects;

/**
 * @author hesheng1024
 * @date 2019/5/26 16:58
 */
public abstract class BaseActivityWithToolbar<P extends BasePresenter> extends BaseActivity<P> {
    private Toolbar mToolbar;
    private TextView mTitle;

    @Override
    protected View inflateContentView() {
        ViewGroup contentView = (ViewGroup) View.inflate(this, R.layout.activity_base, null);
        contentView.addView(initToolbar(contentView), 0);
        View.inflate(this, getLayoutId(), contentView);
        return contentView;
    }

    private View initToolbar(ViewGroup contentView) {
        mToolbar = (Toolbar) getLayoutInflater().inflate(R.layout.include_toolbar, contentView, false);
        if (isTranslucent()) {
            mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        }
        mToolbar.setBackgroundResource(R.drawable.bg_toolbar);
        mToolbar.addView(getLayoutInflater().inflate(getToolbarContent(), mToolbar, false));
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(false);
        initToolbarEvent(mToolbar);
        return mToolbar;
    }

    private void initToolbarEvent(@NonNull Toolbar toolbar) {
        ImageView ivLeft = toolbar.findViewById(R.id.iv_toolbar_left);
        mTitle = toolbar.findViewById(R.id.tv_toolbar_title);
        if (ivLeft != null) {
            ivLeft.setOnClickListener(v -> finish());
        } else {
            LogUtil.e("ivLeft is null, maybe toolbar layout is diff or null");
        }
    }

    protected void setTheTitle(@NonNull String title) {
        if (mTitle != null) {
            mTitle.setText(title);
        } else {
            LogUtil.e("mTitle is null, maybe toolbar layout is diff or null");
        }
    }

    protected void setTheTitle(int resId) {
       setTheTitle(getResources().getText(resId).toString());
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @LayoutRes
    protected int getToolbarContent() {
        return R.layout.include_toolbar_content;
    }
}
