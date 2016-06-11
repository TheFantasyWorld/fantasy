package com.androidworld.app.config;

import android.app.Application;

/**
 * <h3>AndroidWorld启动入口</h3>
 * <p>提供初始化以及配置等操作</p>
 * @author LQC
 *         当前时间：2016/6/4 20:05
 */
public class AndroidWorldApplication extends Application implements Constants, AppSettings {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
