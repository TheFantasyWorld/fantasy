package com.androidworld.app.bean;

import com.androidworld.app.ui.adapter.ExpandableItemAdapter;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class WidgetType extends AbstractExpandableItem<WidgetItem> implements MultiItemEntity {

    public String title;

    public WidgetType(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
