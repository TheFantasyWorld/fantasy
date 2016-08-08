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


/**
 * <h3>事件控制接口</h3>
 * @author LQC
 * 当前时间：2016/8/1 15:26
 */
public interface Event {

    /**
     * 注册事件
     */
    void registerEvent();

    /**
     * 注销事件
     */
    void unregisterEvent();

    /**
     * 当RxBus发送请求后，回调该方法
     *
     * @param rxEvent
     */
    void onCallEvent(RxEvent rxEvent);
}
