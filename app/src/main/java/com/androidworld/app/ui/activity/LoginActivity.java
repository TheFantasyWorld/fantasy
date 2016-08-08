package com.androidworld.app.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

/**
 * <h3>登录界面</h3>
 * @author LQC
 * 当前时间：2016/6/12 17:14
 */
public class LoginActivity extends BaseSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("登录");
        super.initToolbar(toolbar);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_login;
    }
}

