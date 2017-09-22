package com.androidworld.app.ui.activity.button;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

public class ShareButtonActivity extends BaseSwipeBackActivity {

    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ShareButtonView shareButtonView = (ShareButtonView) findViewById(R.id.btn_share);
        shareButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count % 2 == 0) {
                    shareButtonView.reset();
                } else {
                    shareButtonView.startAnimation();
                    count++;
                }
            }
        });
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("ShareButton");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_share_button;
    }
}
