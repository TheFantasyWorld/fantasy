package com.androidworld.app.util;

import android.app.Activity;
import android.content.Context;

import com.androidworld.app.R;


/**
 * 描述：主题工具
 */
public class ThemeUtil {

    public static void changTheme(Activity activity, Theme theme) {
        if (activity == null)
            return;
        int style = R.style.BlueTheme;
        switch (theme) {
            case BROWN:
                style = R.style.BrownTheme;
                break;
            case RED:
                style = R.style.RedTheme;
                break;
            case BLUE:
                style = R.style.BlueTheme;
                break;
            case BLUE_GREY:
                style = R.style.BlueGreyTheme;
                break;
            case YELLOW:
                style = R.style.YellowTheme;
                break;
            case DEEP_PURPLE:
                style = R.style.DeepPurpleTheme;
                break;
            case PINK:
                style = R.style.PinkTheme;
                break;
            case GREEN:
                style = R.style.GreenTheme;
                break;
            default:
                break;
        }
        activity.setTheme(style);
    }

    public static int getDialogStyle(Theme theme) {

        int style = R.style.BlueTheme;
        switch (theme) {
            case BROWN:
                style = R.style.BrownDialogTheme;
                break;
            case RED:
                style = R.style.RedDialogTheme;
                break;
            case BLUE:
                style = R.style.BlueDialogTheme;
                break;
            case BLUE_GREY:
                style = R.style.BlueGreyDialogTheme;
                break;
            case YELLOW:
                style = R.style.YellowDialogTheme;
                break;
            case DEEP_PURPLE:
                style = R.style.DeepPurpleDialogTheme;
                break;
            case PINK:
                style = R.style.PinkDialogTheme;
                break;
            case GREEN:
                style = R.style.GreenDialogTheme;
                break;
            default:
                break;
        }
        return style;
    }

    public static Theme getCurrentTheme(Context context) {
        int value = PreferenceUtils.getPrefInt(context, "theme", Theme.BLUE.mValue);
        return ThemeUtil.Theme.mapValueToTheme(value);
    }

    public enum Theme {
        RED(0x00),
        BROWN(0x01),
        BLUE(0x02),
        BLUE_GREY(0x03),
        YELLOW(0x04),
        DEEP_PURPLE(0x05),
        PINK(0x06),
        GREEN(0x07);

        private int mValue;

        Theme(int value) {
            this.mValue = value;
        }

        public static Theme mapValueToTheme(final int value) {
            for (Theme theme : Theme.values()) {
                if (value == theme.getIntValue()) {
                    return theme;
                }
            }
            // If run here, return default
            return RED;
        }

        public int getIntValue() {
            return mValue;
        }
    }
}
