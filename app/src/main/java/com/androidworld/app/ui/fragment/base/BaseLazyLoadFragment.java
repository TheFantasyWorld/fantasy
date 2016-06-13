package com.androidworld.app.ui.fragment.base;

import android.os.Bundle;
import android.view.View;

/**
 * <h3>延迟加载</h3>
 * @author LQC
 * 当前时间：2016/6/12 21:01
 */
public abstract class BaseLazyLoadFragment extends BaseFragment {

    private boolean isFirstLoad = true;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            onVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        }
    }

    private void onVisible() {
        if (isFirstLoad) {
            isFirstLoad = false;
        }
    }
}
