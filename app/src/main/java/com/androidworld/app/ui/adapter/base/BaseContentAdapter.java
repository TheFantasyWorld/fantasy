package com.androidworld.app.ui.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * <h3>通用适配器</h3>
 * @author LQC
 * 当前时间：2016/6/13 20:38
 */
public abstract class BaseContentAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDataList;
    protected LayoutInflater mInflater;

    public List<T> getDataList() {
        return mDataList;
    }

    public void setDataList(List<T> dataList) {
        this.mDataList = dataList;
    }

    public BaseContentAdapter(Context context, List<T> list) {
        mContext = context;
        mDataList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
