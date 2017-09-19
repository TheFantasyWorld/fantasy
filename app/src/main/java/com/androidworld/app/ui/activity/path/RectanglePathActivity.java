package com.androidworld.app.ui.activity.path;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

import butterknife.Bind;

public class RectanglePathActivity extends BaseSwipeBackActivity {

    @Bind(R.id.path_measure_view)
    PathMeasureView mPathMeasureView;

    @Override
    protected void onResume() {
        super.onResume();
        mPathMeasureView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPathMeasureView.startMove();
            }
        }, 1000);
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("PathMeasure");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_rectangle_path;
    }
}
