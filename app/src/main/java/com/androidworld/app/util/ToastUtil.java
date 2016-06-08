package com.androidworld.app.util;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Toast显示工具类
 * 
 */
public class ToastUtil {
	private static Handler handler = new Handler(Looper.getMainLooper());
	private static Toast toast = null;
	private static Object synObj = new Object();

	public static void showMessage(final Context act, final String msg) {
		showMessage(act, msg, Toast.LENGTH_SHORT);
	}

	public static void showMessage(final Context act, final int msg) {
		showMessage(act, msg, Toast.LENGTH_SHORT);
	}

	public static void showMessageLong(final Context act, final String msg) {
		showMessage(act, msg, Toast.LENGTH_LONG);
	}

	public static void showMessageLong(final Context act, final int msg) {
		showMessage(act, msg, Toast.LENGTH_LONG);
	}

	private static void showMessage(final Context act, final String msg,
			final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						synchronized (synObj) {
							if (toast != null) {
								toast.setText(msg);
								toast.setDuration(len);
							} else {
								toast = Toast.makeText(act, msg, len);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}

	/**
	 * @param act
	 * @param msg
	 * @param len
	 *            资源对应的id
	 */
	private static void showMessage(final Context act, final int msg,
			final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						synchronized (synObj) {
							if (toast != null) {
								toast.setText(msg);
								toast.setDuration(len);
							} else {
								try {
									toast = Toast.makeText(act, msg, len);
								} catch (NotFoundException e) {
									e.printStackTrace();
								}
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}
}
