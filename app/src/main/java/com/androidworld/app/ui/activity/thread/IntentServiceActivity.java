package com.androidworld.app.ui.activity.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseSwipeBackActivity;
import com.androidworld.app.ui.activity.popupwindow.CommonPopupWindow;
import com.androidworld.app.ui.activity.thread.intentService.MyIntentService;
import com.androidworld.app.ui.activity.thread.intentService.UpdateUIReceiver;
import com.androidworld.app.util.DensityUtils;


public class IntentServiceActivity extends BaseSwipeBackActivity implements CommonPopupWindow.ViewInterface, View.OnClickListener  {

    private CommonPopupWindow popupWindow;
    private ProgressBar pb_one, pb_two;
    private TextView tv_progress_one, tv_progress_two;

    @Override
    public int getSubContentViewLayoutId() {
        return R.layout.activity_itent_service;
    }

    @Override
    protected void initToolbar(Toolbar toolbar, View toolbarShadow) {
        toolbar.setTitle("IntentService");
        super.initToolbar(toolbar, toolbarShadow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver.register(this);
        findViewById(R.id.btn_start_one).setOnClickListener(this);
        findViewById(R.id.btn_start_two).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(IntentServiceActivity.this, MyIntentService.class);
        switch (v.getId()) {
            case R.id.btn_start_one:
                intent.setAction(MyIntentService.ACTION_ONE);
                startService(intent);
                break;
            case R.id.btn_start_two:
                intent.setAction(MyIntentService.ACTION_TWO);
                startService(intent);
                break;
        }
        showPopupWindow();
    }

    @Override
    protected void onDestroy() {
        receiver.unRegister(this);
        super.onDestroy();
    }

    //BroadcastReceiver用来更新UI
    private UpdateUIReceiver receiver = new UpdateUIReceiver() {
        @Override
        public void UpdateUI(int type, int progress) {
            switch (type) {
                case 0:
                    //更新第一个进度
                    if (tv_progress_one == null || pb_one == null) return;
                    tv_progress_one.setText(String.valueOf(progress + "%"));
                    pb_one.setProgress(progress);
                    break;
                case 1:
                    //更新第二个进度
                    if (tv_progress_two == null || pb_two == null) return;
                    tv_progress_two.setText(String.valueOf(progress + "%"));
                    pb_two.setProgress(progress);
                    break;
            }
        }
    };

    public void showPopupWindow() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.intent_service, null);
        //测量View的宽高
        DensityUtils.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.intent_service)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, upView.getMeasuredHeight())
                .setBackGroundLevel(1f)//取值范围0.0f-1.0f 值越小越暗
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.TOP, 0, DensityUtils.dp2px(this, 150));
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.intent_service:
                tv_progress_one = (TextView) view.findViewById(R.id.tv_progress_one);
                tv_progress_two = (TextView) view.findViewById(R.id.tv_progress_two);
                pb_one = (ProgressBar) view.findViewById(R.id.pb_one);
                pb_one.setProgress(0);
                pb_two = (ProgressBar) view.findViewById(R.id.pb_two);
                pb_two.setProgress(0);
                break;
        }
    }

}
