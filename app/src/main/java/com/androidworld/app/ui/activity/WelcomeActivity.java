package com.androidworld.app.ui.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;

import butterknife.Bind;

/**
 * <h3>欢迎界面</h3>
 *
 * @author LQC
 *         当前时间：2016/6/13 16:26
 */
public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.header_img)
    ImageView headerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(HomeActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        headerImg.startAnimation(alphaAnimation);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return NO_BASE_CONTENT_VIEW;
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_welcome;
    }

}
