package com.androidworld.app.ui.activity.dialog;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

public class DialogWithHeaderActivity extends BaseSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MaterialStyledDialog.Builder(this)
                .setTitle("You are Lucky!")
                .setDescription("What can we improve? Your feedback is always welcome.")
                .setIcon(R.mipmap.play)
                .setHeaderDrawable(R.mipmap.header)
                .setPositiveText("Ok")
                .setNegativeText("Cancel")
                .show();
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("DialogWithHeader");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_common_dialog;
    }
}
