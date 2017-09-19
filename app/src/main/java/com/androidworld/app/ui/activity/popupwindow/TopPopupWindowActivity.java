package com.androidworld.app.ui.activity.popupwindow;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TopPopupWindowActivity extends BaseSwipeBackActivity implements CommonPopupWindow.ViewInterface {

    private CommonPopupWindow mPopupWindow;

    //向下弹出
    public void showPopupWindow(View view) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) return;
        mPopupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_down)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
        mPopupWindow.showAsDropDown(view);
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("顶部弹窗");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_common_popup_window;
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        List<String> mDataList = new ArrayList<>();
        mDataList.add("瑞兹");
        mDataList.add("奥利安娜");
        mDataList.add("卡特琳娜");
        mDataList.add("盲僧");
        mDataList.add("英雄联盟");
        mDataList.add("永恒梦魇");
        mDataList.add("安妮");
        mDataList.add("贾克斯");
        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new GridLayoutManager(this, 3));
        recycle_view.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.popup_item, mDataList) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.choice_text, item);
            }
        });
    }
}
