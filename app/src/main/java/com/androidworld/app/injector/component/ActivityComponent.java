package com.androidworld.app.injector.component;

import android.app.Activity;

import com.androidworld.app.injector.PerActivity;
import com.androidworld.app.injector.module.ActivityModule;

import dagger.Component;

/**
 * <h3>ActivityComponent</h3>
 * @author LQC
 * 当前时间：2016/6/12 16:29
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
