package com.androidworld.app.util;

import android.graphics.Color;

/**
 * 描述：  颜色处理工具
 * @author 林秋城
 * 当前时间： 2015/11/26.
 */
public class ColorUtils {


    /**
     * 颜色加深处理
     *
     */
    public static int colorBurn(int RGBValues) {
        // int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        red = red < 0 ? 0 : red;
        green = green < 0 ? 0 : green;
        blue = blue < 0 ? 0 : blue;
        return Color.rgb(red, green, blue);
    }

    /**
     * 描述：颜色透明度处理
     */
    public static int alphaColor(int RGBValues,int alpha){
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        red = red < 0 ? 0 : red;
        green = green < 0 ? 0 : green;
        blue = blue < 0 ? 0 : blue;
        return Color.argb(alpha,red, green, blue);

    }


}
