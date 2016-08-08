package com.androidworld.app.ui.widgets.state;


import com.androidworld.app.R;

/**
 * <h3></h3>
 * @author LQC
 * 当前时间：2016/8/3 21:26
 */
public class ProgressState extends AbstractShowState {

    @Override
    public void show(boolean animate) {
        showViewById(R.id.epf_progress, animate);
    }

    @Override
    public void dismiss(boolean animate) {
        dismissViewById(R.id.epf_progress, animate);
    }
}
