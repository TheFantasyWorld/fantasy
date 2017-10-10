package com.androidworld.app.ui.activity.event;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomCardView extends CardView {

    private boolean allowDispatchTouchEvent = true;

    public void setAllowDispatchTouchEvent(boolean allowDispatchTouchEvent) {
        this.allowDispatchTouchEvent = allowDispatchTouchEvent;
    }

    public CustomCardView(Context context) {
        super(context);
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return allowDispatchTouchEvent? super.dispatchTouchEvent(ev) : super.onTouchEvent(ev);
    }

}
