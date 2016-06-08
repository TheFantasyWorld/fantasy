package com.androidworld.app.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class ExitUtil {

	private Boolean isQuit = false;
	private Timer timer = new Timer();
	private Activity activity;
	private View view;

	public ExitUtil(Activity activity, View view) {
		super();
		this.activity = activity;
        this.view = view;
	}

	/**
	 * 退出执行方法
	 */
	public void exit() {
		if (isQuit == false) {
			isQuit = true;
//			Toast.makeText(activity, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            Snackbar.make(view, "再按一次退出程序", Snackbar.LENGTH_LONG).setAction("退出", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                    System.exit(0);
                }
            }).show();
            TimerTask task = null;
			task = new TimerTask() {
				@Override
				public void run() {
					isQuit = false;
				}
			};
			timer.schedule(task, 2000);
		} else {
			activity.finish();
			System.exit(0);
		}
	}
}
