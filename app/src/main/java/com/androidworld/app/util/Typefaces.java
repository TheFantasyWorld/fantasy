package com.androidworld.app.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Hashtable;

public class Typefaces {

  public static final String TAG = Typefaces.class.getSimpleName();
  public static final Hashtable<String, Typeface> cache = new Hashtable<>();

  public Typefaces() {
    // no instances
  }

  public static Typeface get(Context context, String assetPath) {
    synchronized (cache) {
      if (!cache.containsKey(assetPath)) {
        try {
          Typeface t = Typeface.createFromAsset(context.getAssets(), assetPath);
          cache.put(assetPath, t);
        } catch (Exception e) {
          Log.e(TAG, "Could not get typeface '" + assetPath + "' Error: " + e.getMessage());
          return null;
        }
      }
      return cache.get(assetPath);
    }
  }

  public static Typeface getRobotoRegular(Context context) {
    return get(context, "fonts/Roboto-Regular.ttf");
  }

  public static Typeface getRobotoMedium(Context context) {
    return get(context, "fonts/Roboto-Medium.ttf");
  }
}
