package com.androidworld.app.ui.activity.button;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class ShareButtonActivity extends BaseSwipeBackActivity {

    int count = 1;

    @Bind(R.id.btn_share)
    ShareButtonView btnShare;

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("ShareButton");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_share_button;
    }

    @OnClick(R.id.btn_share)
    public void onViewClicked() {
        if (count % 2 == 0) {
            btnShare.reset();
        } else {
            btnShare.startAnimation();
        }
        count++;
    }
}
