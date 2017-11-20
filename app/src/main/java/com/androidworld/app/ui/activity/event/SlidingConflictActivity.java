package com.androidworld.app.ui.activity.event;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * <h3>滑动冲突</h3>
 *
 * @author LQC
 *         当前时间：2017/11/16 14:57
 */
public class SlidingConflictActivity extends BaseActivity {

    @Bind(R.id.lv)
    CustomListView mListView;

    @Bind(R.id.lv2)
    CustomListView mListView2;

    @Bind(R.id.lv3)
    CustomListView mListView3;

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_sliding_conflict;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> data = new ArrayList<>();
        data.add("I'm test data 1```");
        data.add("I'm test data 2```");
        data.add("I'm test data 3```");
        data.add("I'm test data 4```");
        data.add("I'm test data 5```");
        data.add("I'm test data 6```");
        data.add("I'm test data 7```");
        data.add("I'm test data 8```");
        data.add("I'm test data 9```");
        data.add("I'm test data 10```");
        data.add("I'm test data 11```");
        data.add("I'm test data 12```");
        data.add("I'm test data 13```");
        data.add("I'm test data 14```");
        data.add("I'm test data 15```");
        data.add("I'm test data 16```");
        data.add("I'm test data 17```");
        data.add("I'm test data 18```");
        data.add("I'm test data 19```");
        data.add("I'm test data 20```");
        mListView.setAdapter(new ArrayAdapter<>(mContext, R.layout.item_expandable_lv1, R.id.tv_title, data));
        mListView2.setAdapter(new ArrayAdapter<>(mContext, R.layout.item_expandable_lv1, R.id.tv_title, data));
        mListView3.setAdapter(new ArrayAdapter<>(mContext, R.layout.item_expandable_lv1, R.id.tv_title, data));
    }
}
