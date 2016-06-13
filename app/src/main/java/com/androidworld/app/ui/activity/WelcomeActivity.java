package com.androidworld.app.ui.activity;

import android.os.Bundle;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;

/**
 * <h3>欢迎界面</h3>
 * @author LQC
 * 当前时间：2016/6/13 16:26
 */
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
        return NO_SUB_CONTENT_VIEW;
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_welcome;
    }
}
