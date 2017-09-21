package com.androidworld.app.ui.activity.path;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;

public class ThreeBezierActivity extends BaseActivity {

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("三阶贝塞尔曲线");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_three_bezier;
    }
}
