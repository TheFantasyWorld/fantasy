package com.androidworld.app.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * <h3>欢迎界面</h3>
 *
 * @author LQC
 *         当前时间：2016/6/13 16:26
 */
public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.header_img)
    ImageView headerImg;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.choose_signin_button)
    Button chooseSigninButton;

    @Bind(R.id.choose_signup_button)
    Button chooseSignupButton;

    @Bind(R.id.button_group)
    LinearLayout buttonGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.choose_signin_button)
    public void onClick(View v) {
        startActivity(HomeActivity.class);
        finish();
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
