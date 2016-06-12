package com.androidworld.app.injector.component;

import com.androidworld.app.config.AndroidWorldApplication;
import com.androidworld.app.injector.module.ApplicationModule;
import com.androidworld.app.ui.activity.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(AndroidWorldApplication application);

    void inject(BaseActivity baseActivity);
}
