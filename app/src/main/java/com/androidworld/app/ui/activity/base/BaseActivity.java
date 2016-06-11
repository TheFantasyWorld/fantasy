package com.androidworld.app.ui.activity.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;

import com.androidworld.app.R;
import com.androidworld.app.util.ColorUtils;
import com.androidworld.app.util.ThemeUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * <h3>Activty基类</h3>
 *
 * @author LQC
 *         当前时间：2016/6/4 20:18
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    protected Intent mIntent;

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTheme();
        initWindow();

        if (getContentViewLayoutId() != 0) {// 设置主布局,若子类设置过布局
            setContentView(getContentViewLayoutId());
        }

        if (hasActionBar()) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            initToolBar(mToolbar);
        }

        mIntent = new Intent();
        mContext = this;
        init(savedInstanceState);
    }

    /**
     * 初始化主题
     */
    protected final void initTheme() {
        ThemeUtil.Theme theme = ThemeUtil.getCurrentTheme(this);
        ThemeUtil.changTheme(this, theme);
    }

    @TargetApi(19)
    protected final void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(ColorUtils.colorBurn(getColorPrimary()));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    /**
     * 获取应用主调色
     */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    /**
     * 获取应用副调色
     */
    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    /**
     * 初始化View、设置属性、监听事件等 >>>>>子类必须重写该方法
     *
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 获取ContentView的id
     *
     * @return int ContentView的id
     */
    protected abstract int getContentViewLayoutId();

    /**
     * 初始化toolbar
     *
     * @param toolbar
     */
    private void initToolBar(Toolbar toolbar) {
        if (toolbar == null) // 如果布局文件没有找到toolbar,则不设置actionbar
            return;
        if (getActionBarTitle() != null) {
            toolbar.setTitle(getActionBarTitle());
        }

        setSupportActionBar(toolbar);
        if (hasBackButton()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setBackgroundColor(getColorPrimary());
    }

    /**
     * 是否有左上角返回按钮
     *
     * @return
     */
    protected boolean hasBackButton() {
        return true;
    }

    /**
     * 子类可以从写,若不重写默认为程序名字 返回String资源
     *
     * @return
     */
    protected String getActionBarTitle() {
        return this.getResources().getString(R.string.app_name);
    }

    /**
     * 子类布局必须要有toolbar才能返回true 子类可以重写 若不重写默认有toolbar 重写返回false,则无toolbar
     *
     * @return
     */
    protected boolean hasActionBar() {
        return true;
    }

    /**
     * 带动画效果的跳转界面
     *
     * @param clazz
     */
    protected final void startActivity(Class<? extends Activity> clazz) {
        mIntent.setClass(this, clazz);
        startActivity(mIntent);
        showActivityInAnim();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();// 返回
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Activity进入的动画效果
     */
    private void showActivityInAnim() {
        overridePendingTransition(R.anim.activity_right_left_anim, R.anim.activity_exit_anim);
    }

    /**
     * Activity退出的动画效果
     */
    private void showActivityExitAnim() {
        overridePendingTransition(R.anim.activity_exit_anim, R.anim.activity_left_right_anim);
    }

    /**
     * 重写退出Activity，附带动画效果
     */
    @Override
    public void finish() {
        super.finish();
        showActivityExitAnim();
    }

}
