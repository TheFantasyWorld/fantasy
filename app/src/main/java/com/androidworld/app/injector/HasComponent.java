package com.androidworld.app.injector;

/**
 * <h3>拥有Component的标识接口</h3>
 * @author LQC
 * 当前时间：2016/6/12 16:08
 */
public interface HasComponent<C> {
    C getComponent();
}
