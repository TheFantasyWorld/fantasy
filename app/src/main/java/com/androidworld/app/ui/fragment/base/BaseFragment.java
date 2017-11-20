package com.androidworld.app.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidworld.app.ui.activity.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * <h3>Fragment基类</h3>
 *
 * @author LQC
 *         当前时间：2016/6/12 20:58
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 获取内容布局
     * @return layout id
     */
    protected abstract int getContentViewId();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * 显示吐司
     */
    public void showToast(String msg, int... duration) {
        getBaseActivity().showToast(msg, duration);
    }

    public BaseActivity getBaseActivity() {
        return ((BaseActivity) getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
