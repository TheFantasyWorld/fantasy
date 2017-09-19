package com.androidworld.app.ui.activity.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

@SuppressWarnings("all")
public class CommonPopupWindow extends PopupWindow {

    private final PopupController mController;

    @Override
    public int getWidth() {
        return mController.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return mController.mPopupView.getMeasuredHeight();
    }

    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    private CommonPopupWindow(Context context) {
        mController = new PopupController(context, this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mController.setBackGroundLevel(1.0f);
    }

    public static class Builder {
        private final PopupController.PopupParams params;
        private ViewInterface listener;

        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        /**
         * 设置子View
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;
            params.bg_level = level;
            return this;
        }

        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }

        public CommonPopupWindow create() {
            final CommonPopupWindow popupWindow = new CommonPopupWindow(params.mContext);
            params.apply(popupWindow.mController);
            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(popupWindow.mController.mPopupView, params.layoutResId);
            }
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            popupWindow.mController.mPopupView.measure(widthMeasureSpec, heightMeasureSpec);
            return popupWindow;
        }
    }

    private static class PopupController {
        private int layoutResId;//布局id
        private Context mContext;
        private PopupWindow mPopupWindow;
        View mPopupView;//弹窗布局View
        private View mView;
        private Window mWindow;

        PopupController(Context context, PopupWindow popupWindow) {
            this.mContext = context;
            this.mPopupWindow = popupWindow;
        }

        public void setView(int layoutResId) {
            mView = null;
            this.layoutResId = layoutResId;
            installContent();
        }

        public void setView(View view) {
            mView = view;
            this.layoutResId = 0;
            installContent();
        }

        private void installContent() {
            if (layoutResId != 0) {
                mPopupView = LayoutInflater.from(mContext).inflate(layoutResId, null);
            } else if (mView != null) {
                mPopupView = mView;
            }
            mPopupWindow.setContentView(mPopupView);
        }

        /**
         * 设置宽度
         *
         * @param width  宽
         * @param height 高
         */
        private void setWidthAndHeight(int width, int height) {
            if (width == 0 || height == 0) {
                //如果没设置宽高，默认是WRAP_CONTENT
                mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                mPopupWindow.setWidth(width);
                mPopupWindow.setHeight(height);
            }
        }


        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         */
        void setBackGroundLevel(float level) {
            mWindow = ((Activity) mContext).getWindow();
            WindowManager.LayoutParams params = mWindow.getAttributes();
            params.alpha = level;
            mWindow.setAttributes(params);
        }


        /**
         * 设置动画
         */
        private void setAnimationStyle(int animationStyle) {
            mPopupWindow.setAnimationStyle(animationStyle);
        }

        /**
         * 设置Outside是否可点击
         *
         * @param touchable 是否可点击
         */
        private void setOutsideTouchable(boolean touchable) {
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置透明背景
            mPopupWindow.setOutsideTouchable(touchable);//设置outside可点击
            mPopupWindow.setFocusable(touchable);
        }


        private static class PopupParams {
            private int layoutResId;//布局id
            private Context mContext;
            private int mWidth, mHeight;//弹窗的宽和高
            private boolean isShowBg, isShowAnim;
            private float bg_level;//屏幕背景灰色程度
            private int animationStyle;//动画Id
            private View mView;
            private boolean isTouchable = true;

            private PopupParams(Context mContext) {
                this.mContext = mContext;
            }

            private void apply(PopupController controller) {
                if (mView != null) {
                    controller.setView(mView);
                } else if (layoutResId != 0) {
                    controller.setView(layoutResId);
                } else {
                    throw new IllegalArgumentException("PopupView's contentView is null");
                }
                controller.setWidthAndHeight(mWidth, mHeight);
                controller.setOutsideTouchable(isTouchable);//设置outside可点击
                if (isShowBg) {
                    //设置背景
                    controller.setBackGroundLevel(bg_level);
                }
                if (isShowAnim) {
                    controller.setAnimationStyle(animationStyle);
                }
            }
        }
    }

}
