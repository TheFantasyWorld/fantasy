/*
 * Copyright 2016. SHENQINCI(沈钦赐)<946736079@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidworld.app.rxbus;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * <h3>RxEvent</h3>
 *
 * @author LQC
 *         当前时间：2016/8/1 14:31
 */
public class RxEvent {

    /** 无动画重启Activity */
    public static final int RESTART_WITH_NO_ANIMATION = 0x01;

    public int type;

    public RxEvent(int type) {
        this.type = type;
    }

    public boolean isType(int type) {
        return this.type == type;
    }

    /**
     * how to use this
     */
    private void howToUse() {
        //发送
        RxEventBus.getInstance().send(new RxEvent(RESTART_WITH_NO_ANIMATION));
        //订阅
        RxEventBus.getInstance().toObserverable()
                .filter(new Func1<Object, Boolean>() {
                    @Override
                    public Boolean call(Object o) {
                        return o instanceof RxEvent;
                    }
                }) //Only accept RxEvent
                .map(new Func1<Object, RxEvent>() {
                    @Override
                    public RxEvent call(Object o) {
                        return (RxEvent)o;
                    }
                })
                .filter(new Func1<RxEvent, Boolean>() {
                    @Override
                    public Boolean call(RxEvent rxEvent) {
                        return rxEvent.isType(RESTART_WITH_NO_ANIMATION);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxEvent>() {
                    @Override
                    public void call(RxEvent rxEvent) {
                        System.out.print("get it!");
                    }
                });
    }
}
