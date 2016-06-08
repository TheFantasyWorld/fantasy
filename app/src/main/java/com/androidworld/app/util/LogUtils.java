package com.androidworld.app.util;


import android.util.Log;

/**
 * 描述：对日志进行管理
 * 在DeBug模式开启，其它模式关闭
 * @author 林秋城
 *         当前时间： 2015/11/11.
 */
public class LogUtils {

    /**
     * 是否开启debug
     */
    public static boolean isDebug = true;


    /**
     * 错误
     *
     * @param clazz
     * @param msg
     */
    public static void e(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.e(clazz.getSimpleName(), msg + "");
        }
    }

    /**
     * 信息
     *
     * @param clazz
     * @param msg
     */
    public static void i(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.i(clazz.getSimpleName(), msg + "");
        }
    }

    /**
     * 警告
     *
     * @param clazz
     * @param msg
     */
    public static void w(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.w(clazz.getSimpleName(), msg + "");
        }
    }
}