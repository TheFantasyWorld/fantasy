package com.androidworld.app.injector.module;

import android.app.Service;

import com.androidworld.app.injector.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * <h3>ServiceModule</h3>
 * @author LQC
 * 当前时间：2016/6/12 16:34
 */
@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    public Service provideService() {
        return mService;
    }
}
