package com.androidworld.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.androidworld.app.R;
import com.androidworld.app.bean.WidgetItem;
import com.androidworld.app.bean.WidgetType;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;
import com.androidworld.app.ui.activity.piechart.PieChartActivity;
import com.androidworld.app.ui.adapter.ExpandableItemAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * <h3>常用控件</h3>
 *
 * @author LQC
 *         当前时间：2016/8/8 15:46
 */
public class WidgetsActivity extends BaseSwipeBackActivity {

    @Bind(R.id.rv_widgets)
    RecyclerView rvWidgets;

    ExpandableItemAdapter mExpandableItemAdapter;

    ArrayList<MultiItemEntity> mDataList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createData();
        rvWidgets.setLayoutManager(new LinearLayoutManager(this));
        mExpandableItemAdapter = new ExpandableItemAdapter(mDataList);
        rvWidgets.setAdapter(mExpandableItemAdapter);
        mExpandableItemAdapter.expandAll();
    }

    private void createData() {
        WidgetType customViews = new WidgetType("CustomViews");
        customViews.addSubItem(new WidgetItem("饼图", PieChartActivity.class));
        customViews.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(customViews);

        WidgetType dialogs = new WidgetType("CustomDialogs");
        dialogs.addSubItem(new WidgetItem("a", SettingsActivity.class));
        dialogs.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(dialogs);

        WidgetType popupWindows = new WidgetType("CustomPopupWindows");
        popupWindows.addSubItem(new WidgetItem("a", SettingsActivity.class));
        popupWindows.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(popupWindows);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("控件");
        super.initToolbar(toolbar);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_widgets;
    }
}
