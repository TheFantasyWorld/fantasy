package com.androidworld.app.ui.activity;

import android.os.Bundle;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return -1;
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_welcome;
    }
}
