package com.androidworld.app.ui.activity.thread;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

import butterknife.OnClick;

public class ThreadActivity extends BaseSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("线程");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_thread;
    }

    @OnClick({R.id.tv_view_one, R.id.tv_view_two, R.id.tv_view_three, R.id.tv_view_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_view_one:
                startActivity(HandlerThreadActivity.class);
                break;

            case R.id.tv_view_two:
                startActivity(ThreadPoolActivity.class);
                break;

            case R.id.tv_view_three:
                startActivity(IntentServiceActivity.class);
                break;

            case R.id.tv_view_four:
                break;
        }
    }
}
