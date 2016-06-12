package com.androidworld.app.injector.component;

import android.app.Service;

import com.androidworld.app.injector.PerService;
import com.androidworld.app.injector.module.ServiceModule;

import dagger.Component;

/**
 * <h3>ServiceComponent</h3>
 * @author LQC 
 * 当前时间：2016/6/12 16:34
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = {ServiceModule.class})
public interface ServiceComponent {

    Service getServiceContext();
}
