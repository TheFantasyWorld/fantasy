package com.androidworld.app.ui.activity.event;

import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;
import com.androidworld.app.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class OnTouchEventActivity extends BaseSwipeBackActivity {

    @Bind(R.id.btn_child)
    CustomButton btnChild;

    @Bind(R.id.cv_parent)
    CustomCardView cvParent;

    @Bind(R.id.switch_activity_event)
    Switch mSwitchActivityEvent;

    @Bind(R.id.switch_parent_event)
    Switch mSwitchParentEvent;

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("事件分发");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_on_touch_event;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mSwitchActivityEvent.isChecked() && super.dispatchTouchEvent(ev);
    }

    @OnClick({R.id.btn_child, R.id.cv_parent, R.id.switch_parent_event})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_child:
                ToastUtil.showMessage(mContext, "子View被点击了！");
                break;

            case R.id.cv_parent:
                ToastUtil.showMessage(mContext, "父ViewGroup被点击了！");
                break;

            case R.id.switch_parent_event:
                cvParent.setAllowDispatchTouchEvent(mSwitchParentEvent.isChecked());
                break;
        }
    }
}
