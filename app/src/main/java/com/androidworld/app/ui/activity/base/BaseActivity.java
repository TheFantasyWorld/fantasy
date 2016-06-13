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
import android.widget.LinearLayout;

import com.androidworld.app.R;
import com.androidworld.app.config.AndroidWorldApplication;
import com.androidworld.app.util.ThemeUtil;

import javax.inject.Inject;

/**
 * <h3>Activty基类</h3>
 *
 * @author LQC
 *         当前时间：2016/6/4 20:18
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Intent mIntent;

    @Inject
    protected Context mContext;

    /**子类不需要继承activity_base布局*/
    protected static final int NO_SUB_CONTENT_VIEW = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAndroidWorldApplication().getApplicationComponent().inject(this);
        initTheme();
        initWindow();
        initInjector();
        initView();
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
            //设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置导航栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintColor(getColorPrimary());
//            tintManager.setStatusBarTintEnabled(true);
        }
    }

    /**
     * 初始化布局
     */
    private void initView() {
        if (getSubContentViewLayoutId() != NO_SUB_CONTENT_VIEW) {  // 若子类设置过布局,则为继承activity_base布局
            setContentView(R.layout.activity_base);
            LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
            getLayoutInflater().inflate(getSubContentViewLayoutId(), contentLayout);
        } else {
            setContentView(getContentViewLayoutId());
        }

        if (hasToolbar()) {  //  当有Toobar时对其进行初始化操作
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            initToolbar(toolbar);
        }
    }

    /**
     * 初始化依赖注入
     */
    protected void initInjector() {
        // >>>>>>需要的时候去重写<<<<<<
    }

    /**
     * 当该方法返回值不为-1时，则为继承activity_base布局，将替换content布局
     *
     * @return int 子布局的id
     */
    protected abstract int getSubContentViewLayoutId();

    /**
     * 获取ContentViewLayoutId
     */
    protected int getContentViewLayoutId() {
        return 0;  //>>>>>>默认值为0，当子类布局不需要继承activity_base的时候，重写该方法<<<<<<
    }

    /**
     * 初始化toolbar
     */
    protected void initToolbar(Toolbar toolbar) {
        if (toolbar == null) // 如果布局文件没有找到toolbar,则不设置toolbar
            return;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackButton());
        }
        toolbar.setBackgroundColor(getColorPrimary());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();  // 返回
                break;
        }
        return super.onOptionsItemSelected(item);
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
     * 是否有左上角返回按钮
     */
    protected boolean hasBackButton() {
        return true;
    }

    /**
     * 子类布局必须要有toolbar才能返回true 子类可以重写 若不重写默认有toolbar 重写返回false,则无toolbar
     */
    protected boolean hasToolbar() {
        return true;
    }

    protected AndroidWorldApplication getAndroidWorldApplication() {
        return (AndroidWorldApplication) getApplication();
    }

    /**
     * 带动画效果的跳转界面
     */
    protected final void startActivity(Class<? extends Activity> clazz) {
        mIntent.setClass(this, clazz);
        startActivity(mIntent);
        showActivityInAnim();
    }

    /**
     * Activity进入的动画效果
     */
    protected void showActivityInAnim() {
        overridePendingTransition(R.anim.activity_right_left_anim, R.anim.activity_exit_anim);
    }

    /**
     * Activity退出的动画效果
     */
    protected void showActivityExitAnim() {
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

    /**
     * 无动画重启Activity
     */
    protected void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
