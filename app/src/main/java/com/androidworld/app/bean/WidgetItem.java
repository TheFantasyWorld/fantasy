package com.androidworld.app.bean;

import com.androidworld.app.ui.adapter.ExpandableItemAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author LQC
 *         当前时间：2017/9/12 15:06
 */
public class WidgetItem implements MultiItemEntity {

    public String title;
    public Class activity;

    public WidgetItem(String title, Class activity) {
        this.title = title;
        this.activity = activity;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}
