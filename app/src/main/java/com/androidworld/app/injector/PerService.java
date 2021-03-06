package com.androidworld.app.injector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h3>自定义作用范围为Service</h3>
 * @author LQC
 * 当前时间：2016/6/12 16:10
 */
@Scope
@Retention(RUNTIME)
public @interface PerService {
}
