package com.androidworld.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;
import com.androidworld.app.ui.fragment.SettingsFragment;

/**
 * <h3>设置界面</h3>
 * @author LQC
 * 当前时间：2016/6/13 17:26
 */
public class SettingsActivity extends BaseSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingsFragment settingFragment = SettingsFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.fl_content, settingFragment).commit();
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.settings);
        super.initToolbar(toolbar);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_settings;
    }
}
