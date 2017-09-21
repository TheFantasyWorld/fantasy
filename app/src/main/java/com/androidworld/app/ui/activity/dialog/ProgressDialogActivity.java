package com.androidworld.app.ui.activity.dialog;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

public class ProgressDialogActivity extends BaseSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AllDialogFragment.newInstance(AllDialogFragment.Type.PROGRESS).show(getSupportFragmentManager(), "progress");
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("Progress加载框");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_common_dialog;
    }
}
