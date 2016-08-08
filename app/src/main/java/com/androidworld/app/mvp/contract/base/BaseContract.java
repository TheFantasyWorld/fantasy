package com.androidworld.app.mvp.contract.base;

import android.support.annotation.NonNull;

/**
 * <h3>契约基类</h3>
 * <p>将MVP中的View和Presenter的接口整合在一个类中，便于维护</p>
 * @author LQC
 *         当前时间：2016/6/11 10:08
 */
public class BaseContract {

    /**
     * <h3>MVP中V的父接口</h3>
     */
    public interface V {

    }

    /**
     * <h3>MVP中P的父接口</h3>
     * @param <T> extends BaseView
     */
    public interface P<T extends V> {

        /**
         * 注入View，使之能够与View相互响应
         *
         * @param view
         */
        void attachView(@NonNull T view);

        /**
         * 释放资源
         */
        void detachView();
    }
}
