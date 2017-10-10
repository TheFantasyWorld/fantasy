package com.androidworld.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.bean.WidgetItem;
import com.androidworld.app.bean.WidgetType;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;
import com.androidworld.app.ui.activity.button.CartButtonActivity;
import com.androidworld.app.ui.activity.button.ShareButtonActivity;
import com.androidworld.app.ui.activity.dialog.DialogWithHeaderActivity;
import com.androidworld.app.ui.activity.dialog.ListViewDialogActivity;
import com.androidworld.app.ui.activity.dialog.ProgressDialogActivity;
import com.androidworld.app.ui.activity.dialog.SwipeAwayDialogActivity;
import com.androidworld.app.ui.activity.dialog.TimePikerDialogActivity;
import com.androidworld.app.ui.activity.event.DispatchTouchEventActivity;
import com.androidworld.app.ui.activity.event.OnInterceptTouchEventActivity;
import com.androidworld.app.ui.activity.event.OnTouchEventActivity;
import com.androidworld.app.ui.activity.path.QQPointActivity;
import com.androidworld.app.ui.activity.path.RectanglePathActivity;
import com.androidworld.app.ui.activity.path.ThreeBezierActivity;
import com.androidworld.app.ui.activity.path.TwoBezierActivity;
import com.androidworld.app.ui.activity.piechart.PieChartActivity;
import com.androidworld.app.ui.activity.popupwindow.BottomPopupWindowActivity;
import com.androidworld.app.ui.activity.popupwindow.LeftPopupWindowActivity;
import com.androidworld.app.ui.activity.popupwindow.RightPopupWindowActivity;
import com.androidworld.app.ui.activity.popupwindow.TopPopupWindowActivity;
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
    }

    private void createData() {
        WidgetType dispatchTouchEvent = new WidgetType("事件分发机制");
        dispatchTouchEvent.addSubItem(new WidgetItem("dispatchTouchEvent详解", DispatchTouchEventActivity.class));
        dispatchTouchEvent.addSubItem(new WidgetItem("onInterceptTouchEvent详解", OnInterceptTouchEventActivity.class));
        dispatchTouchEvent.addSubItem(new WidgetItem("onTouchEvent详解", OnTouchEventActivity.class));
        dispatchTouchEvent.addSubItem(new WidgetItem("解决ScrollView与ListView事件冲突", SettingsActivity.class));
        mDataList.add(dispatchTouchEvent);

        WidgetType customViews = new WidgetType("自定义Views");
        customViews.addSubItem(new WidgetItem("饼图", PieChartActivity.class));
        customViews.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(customViews);

        WidgetType animates = new WidgetType("View动画");
        animates.addSubItem(new WidgetItem("值动画", ShareButtonActivity.class));
        animates.addSubItem(new WidgetItem("补间动画", SettingsActivity.class));
        mDataList.add(animates);

        WidgetType dialogs = new WidgetType("Dialogs");
        dialogs.addSubItem(new WidgetItem("DialogWithHeader", DialogWithHeaderActivity.class));
        dialogs.addSubItem(new WidgetItem("滑动取消对话框", SwipeAwayDialogActivity.class));
        dialogs.addSubItem(new WidgetItem("时间选择对话框", TimePikerDialogActivity.class));
        dialogs.addSubItem(new WidgetItem("ListViewDialog", ListViewDialogActivity.class));
        dialogs.addSubItem(new WidgetItem("Progress加载框", ProgressDialogActivity.class));
        mDataList.add(dialogs);

        WidgetType popupWindows = new WidgetType("PopupWindows");
        popupWindows.addSubItem(new WidgetItem("顶部弹窗", TopPopupWindowActivity.class));
        popupWindows.addSubItem(new WidgetItem("底部弹窗", BottomPopupWindowActivity.class));
        popupWindows.addSubItem(new WidgetItem("左弹窗", LeftPopupWindowActivity.class));
        popupWindows.addSubItem(new WidgetItem("右弹窗", RightPopupWindowActivity.class));
        mDataList.add(popupWindows);

        WidgetType paths = new WidgetType("Path绘制");
        paths.addSubItem(new WidgetItem("PathMeasure", RectanglePathActivity.class));
        paths.addSubItem(new WidgetItem("二阶贝塞尔曲线", TwoBezierActivity.class));
        paths.addSubItem(new WidgetItem("三阶贝塞尔曲线", ThreeBezierActivity.class));
        paths.addSubItem(new WidgetItem("贝塞尔实现QQ小红点", QQPointActivity.class));
        mDataList.add(paths);

        WidgetType viewPagers = new WidgetType("ViewPagers");
        viewPagers.addSubItem(new WidgetItem("a", RectanglePathActivity.class));
        viewPagers.addSubItem(new WidgetItem("b", SettingsActivity.class));
        viewPagers.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(viewPagers);

        WidgetType listViews = new WidgetType("ListView");
        listViews.addSubItem(new WidgetItem("a", RectanglePathActivity.class));
        listViews.addSubItem(new WidgetItem("b", SettingsActivity.class));
        listViews.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(listViews);

        WidgetType recyclerViews = new WidgetType("RecyclerView");
        recyclerViews.addSubItem(new WidgetItem("瀑布流＋照片墙", RectanglePathActivity.class));
        recyclerViews.addSubItem(new WidgetItem("b", SettingsActivity.class));
        recyclerViews.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(recyclerViews);

        WidgetType textViews = new WidgetType("TextView");
        textViews.addSubItem(new WidgetItem("a", RectanglePathActivity.class));
        textViews.addSubItem(new WidgetItem("b", SettingsActivity.class));
        textViews.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(textViews);

        WidgetType buttons = new WidgetType("Button");
        buttons.addSubItem(new WidgetItem("ShareButton", ShareButtonActivity.class));
        buttons.addSubItem(new WidgetItem("购物车添加按钮", CartButtonActivity.class));
        buttons.addSubItem(new WidgetItem("c", SettingsActivity.class));
        mDataList.add(buttons);
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("控件");
        toolbarShadow.setVisibility(View.GONE);
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_widgets;
    }
}
