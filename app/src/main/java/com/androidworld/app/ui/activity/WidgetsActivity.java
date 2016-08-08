package com.androidworld.app.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

/**
 * <h3>常用控件</h3>
 *
 * @author LQC
 *         当前时间：2016/8/8 15:46
 */
public class WidgetsActivity extends BaseSwipeBackActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_widgets;
    }
}
