package com.androidworld.app.injector.module;

import android.app.Activity;

import com.androidworld.app.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * <h3>ActivityModule</h3>
 * @author LQC
 * 当前时间：2016/6/12 16:32
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }
}
