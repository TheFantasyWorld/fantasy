package com.androidworld.app.ui.activity.dialog;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

public class TimePikerDialogActivity extends BaseSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AllDialogFragment.newInstance(AllDialogFragment.Type.TIME).show(getSupportFragmentManager(), "time");
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("时间选择对话框");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_common_dialog;
    }
}
