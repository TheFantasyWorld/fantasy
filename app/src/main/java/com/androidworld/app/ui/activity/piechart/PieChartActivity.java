package com.androidworld.app.ui.activity.piechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class PieChartActivity extends BaseSwipeBackActivity {

    @Bind(R.id.cake_view)
    CakeView mCakeView;

    private String[] mNames = {"php", "object-c", "c", "c++", "java", "android", "linux"};
    private float[] mValues = {2f, 2f, 3f, 4f, 5f, 6f, 7f};
    private int[] mColArrays = {Color.RED, Color.parseColor("#4EBCD3"), Color.MAGENTA, Color.YELLOW, Color.GREEN, Color.parseColor("#F68B2B"), Color.parseColor("#6FB30D")};//圆弧颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCakeView.setData(generateData());
    }

    /**
     * 生成数据
     */
    private List<CakeBean> generateData() {
        List<CakeBean> beans = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            CakeBean bean = new CakeBean();
            bean.name = mNames[i];
            bean.value = mValues[i];
            bean.mColor = mColArrays[i];
            beans.add(bean);
        }
        return beans;
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("饼图");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_pie_chart;
    }
}
