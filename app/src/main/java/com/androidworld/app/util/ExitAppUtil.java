package com.androidworld.app.util;

import android.app.Activity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ExitAppUtil {

	private Boolean isQuit = false;
	private Timer timer = new Timer();
	private Activity mActivity;

	public ExitAppUtil(Activity activity) {
		this.mActivity = activity;
	}

	/**
	 * 退出执行方法
	 */
	public void exit() {
		if (!isQuit) {
			isQuit = true;
			Toast.makeText(mActivity, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            TimerTask task  = new TimerTask() {
				@Override
				public void run() {
					isQuit = false;
				}
			};
			timer.schedule(task, 2000);
		} else {
			mActivity.finish();
			mActivity = null;
		}
	}
}
