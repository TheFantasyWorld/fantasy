package com.androidworld.app.mvp.contract.base;

import android.support.annotation.NonNull;

/**
 * <h3>契约基类</h3>
 *
 * @author LQC
 *         当前时间：2016/6/11 10:08
 */
public class BaseContract {

    public interface BaseView {

    }

    public interface BasePresenter<T extends BaseView> {

        void attachView(@NonNull T view);

        void detachView();
    }
}
