package com.androidworld.app.ui.activity.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class PathMeasureView extends View {
    private Paint mPaint;
    private Path mPath;
    private PathMeasure pathMeasure;
    private RectF mRectF;
    private float[] pos = new float[2];
    private float[] tan = new float[2];

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化Paint
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.BLACK);
        //初始化Path
        mPath = new Path();
        //初始化PathMeasure
        pathMeasure = new PathMeasure();
        //初始化RectF
        mRectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int centerX = w / 2;
        int centerY = h / 2;
        int halfWidth = 200;

        mRectF.set(centerX - halfWidth, centerY - halfWidth, centerX + halfWidth, centerY + halfWidth);
        pos[0] = centerX - halfWidth;
        pos[1] = centerY - halfWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制矩形
        mPath.reset();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addRect(mRectF, Path.Direction.CW);
        pathMeasure.setPath(mPath, false);
        canvas.drawPath(mPath, mPaint);
        //绘制圆
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(pos[0], pos[1], 12, mPaint);
    }

    public void startMove() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        animator.setDuration(3 * 1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                //tan[0]是邻边 tan[1]是对边
                pathMeasure.getPosTan(distance, pos, tan);
                postInvalidate();
            }
        });
        animator.start();
    }
}
