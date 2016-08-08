package com.androidworld.app.mvp.contract;

import com.androidworld.app.mvp.contract.base.BaseContract;

/**
 * <h3></h3>
 *
 * @author LQC
 *         当前时间：2016/6/11 10:07
 */
public class C extends BaseContract {

    public interface View extends V {

    }

    public interface Presenter extends P<View> {

    }
}
