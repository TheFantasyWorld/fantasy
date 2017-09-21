package com.androidworld.app.ui.activity.path;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

public class QQPointActivity extends BaseSwipeBackActivity {

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("QQ小红点");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_qq_point;
    }
}
