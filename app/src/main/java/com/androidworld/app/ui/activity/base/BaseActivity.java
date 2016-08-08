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
import com.androidworld.app.rxbus.Event;
import com.androidworld.app.rxbus.RxEvent;
import com.androidworld.app.rxbus.RxEventBus;
import com.androidworld.app.util.ThemeUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * <h3>Activty基类</h3>
 *
 * @author LQC
 *         当前时间：2016/6/4 20:18
 */
public abstract class BaseActivity extends AppCompatActivity implements Event {

    @Inject
    protected Intent mIntent;

    @Inject
    protected Context mContext;

    /**
     * 子类不需要继承activity_base布局
     */
    protected static final int NO_BASE_CONTENT_VIEW = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAndroidWorldApplication().getApplicationComponent().inject(this);
        initTheme();
        initWindow();
        initInjector();
        initView();
        registerEvent();
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
        if (getSubContentViewLayoutId() != NO_BASE_CONTENT_VIEW) {  // 若子类设置过布局,则为继承activity_base布局
            setContentView(R.layout.activity_base);
            LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
            getLayoutInflater().inflate(getSubContentViewLayoutId(), contentLayout);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            initToolbar(toolbar);
        } else {
            setContentView(getContentViewLayoutId());
        }
        ButterKnife.bind(this);
    }

    /**
     * 初始化依赖注入
     */
    protected void initInjector() {
        // >>>>>>需要的时候去重写<<<<<<
    }

    /**
     * 当该方法返回值不为0时，则为继承activity_base布局，将替换content布局
     *
     * @return int 子布局的id
     */
    protected abstract int getSubContentViewLayoutId();

    /**
     * 获取ContentViewLayoutId
     */
    protected int getContentViewLayoutId() {
        return -1;  //>>>>>>默认值为-1，当子类布局不需要继承activity_base的时候，重写该方法<<<<<<
    }

    /**
     * 初始化toolbar
     */
    protected void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackButton());
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
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        super.finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    //用于接收事件
    protected Subscription mSubscribe;

    @Override
    public void registerEvent() {
        //订阅
        mSubscribe = RxEventBus.getInstance().toObserverable()
                .filter(new Func1<Object, Boolean>() {
                    @Override
                    public Boolean call(Object o) {
                        return o instanceof RxEvent;
                    }
                }) //Only accept RxEvent
                .map(new Func1<Object, RxEvent>() {
                    @Override
                    public RxEvent call(Object o) {
                        return (RxEvent)o;
                    }
                })
                .filter(new Func1<RxEvent, Boolean>() {
                    @Override
                    public Boolean call(RxEvent rxEvent) {
                        return rxEvent.isType(RxEvent.RESTART_WITH_NO_ANIMATION);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxEvent>() {
                    @Override
                    public void call(RxEvent rxEvent) {
                        onCallEvent(rxEvent);
                    }
                });
    }

    @Override
    public void unregisterEvent() {
        if (mSubscribe != null) {
            mSubscribe.unsubscribe();
        }
    }

    @Override
    public void onCallEvent(RxEvent e) {
        reload();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        unregisterEvent();
        super.onDestroy();
    }
}
