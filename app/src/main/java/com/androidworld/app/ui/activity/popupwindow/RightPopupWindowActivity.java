package com.androidworld.app.ui.activity.popupwindow;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

public class RightPopupWindowActivity extends BaseSwipeBackActivity implements CommonPopupWindow.ViewInterface {

    private CommonPopupWindow mPopupWindow;

    public void showPopupWindow(View view) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) return;
        mPopupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_left_or_right)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimRight)
                .setViewOnclickListener(this)
                .create();
        mPopupWindow.showAsDropDown(view, mPopupWindow.getWidth(), -view.getHeight() * 2);
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("右弹窗");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_common_popup_window;
    }

    @Override
    public void getChildView(View view, int layoutResId) {
    }
}
