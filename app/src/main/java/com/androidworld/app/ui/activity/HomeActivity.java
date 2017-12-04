package com.androidworld.app.ui.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidworld.app.R;
import com.androidworld.app.bean.Blog;
import com.androidworld.app.ui.activity.base.TakePictureActivity;
import com.androidworld.app.ui.activity.thread.ThreadActivity;
import com.androidworld.app.util.ExitAppUtil;
import com.androidworld.app.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.androidworld.app.R.id.nav_view;

/**
 * <h3>主界面</h3>
 * <p>负责设置侧滑菜单以及主页面内容页切换</p>
 *
 * @author LQC
 *         当前时间：2016/6/4 20:28
 */
public class HomeActivity extends TakePictureActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Bind(nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.rv_blog)
    RecyclerView rvBlog;

    @Bind(R.id.banner)
    Banner mBanner;

    @Bind(R.id.et_search)
    EditText etSearch;

    ImageView ivUserAvatar;

    private ExitAppUtil mExitAppUtil = new ExitAppUtil(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        initDrawerLayout();
        initData();
    }

    private void initData() {
        List<String> images = new ArrayList<>();
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=414758921,2065512525&fm=27&gp=0.jpg");
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3925610419,1115789951&fm=27&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3556951346,3286198843&fm=27&gp=0.jpg");
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        List<Blog> blogList = new ArrayList<>();
        blogList.add(new Blog("http://www.jianshu.com/p/525ccf61db94", "实践自定义UI－ViewGroup", R.mipmap.bg_a1, "2017.11.27 04:47"));
        blogList.add(new Blog("http://blog.csdn.net/cym492224103/article/details/73239306", "花了 4 个月整理了 50 篇 Android 干货文章", R.mipmap.bg_a2, "2017.6.14 15:38"));
        blogList.add(new Blog("http://www.jianshu.com/p/38015afcdb58", "Android事件分发机制详解：史上最全面、最易懂", R.mipmap.bg_a2, "2017.5.24 15:38"));
        blogList.add(new Blog("http://gank.io/post/56e80c2c677659311bed9841?from=timeline&isappinstalled=0&nsukey=g1D1Y6PMp3BW%2B0%2F%2Butx4StSJxcUCTm4%2BN8T7LnPNCCeQEY1lzm6oKvXdbrlAD4E9T%2FB1quV75jJB7H9zjcRxTQ%3D%3D", "RxJava 与 Retrofit 结合的最佳实践", R.mipmap.bg_a2, "2017.4.17 15:38"));
        blogList.add(new Blog("http://blog.csdn.net/leeo1010/article/details/49903759", "Android Studio多渠道打包和代码混淆教程", R.mipmap.bg_a2, "2016.8.15 12:08"));
        blogList.add(new Blog("http://www.cnblogs.com/maowang1991/archive/2013/04/15/3023236.html", "Java开发中的23种设计模式详解", R.mipmap.bg_a2, "2016.11.26 22:51"));
        rvBlog.setLayoutManager(new LinearLayoutManager(mContext));
        rvBlog.setAdapter(new BaseQuickAdapter<Blog, BaseViewHolder>(R.layout.item_blog, blogList) {
            @Override
            protected void convert(final BaseViewHolder helper, final Blog item) {
                helper.setText(R.id.tv_title, item.title);
                helper.setText(R.id.tv_start_date, item.date);
                helper.setBackgroundRes(R.id.iv_android, item.imageUri);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((TextView)helper.getView(R.id.tv_title)).setTextColor(0xFF727272);
                        WebActivity.openWebActivity(HomeActivity.this, item.uri);
                    }
                });
            }
        });
    }

    /**
     * 初始化侧滑菜单
     */
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
            //  默认停留在主页
            mNavigationView.setCheckedItem(R.id.home);
            View drawView = mNavigationView.getHeaderView(0);
            ivUserAvatar = (ImageView) drawView.findViewById(R.id.iv_user_avatar);
            ivUserAvatar.setOnClickListener(mOnClickListener);
        }
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return NO_BASE_CONTENT_VIEW;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            mExitAppUtil.exit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                mDrawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.widget:
                startActivity(WidgetsActivity.class);
                break;

            case R.id.thread:
                startActivity(ThreadActivity.class);
                break;

            case R.id.settings:
                startActivity(SettingsActivity.class);
                break;

            case R.id.nav_data:
                ToastUtil.showMessage(mContext, "功能待开发！");
                break;

            case R.id.nav_algorithm:
                ToastUtil.showMessage(mContext, "功能待开发！");
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    protected ImageView getImageView() {
        return ivUserAvatar;
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //注意：
            //1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
            //2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
            //传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
            //切记不要胡乱强转！
            //Glide 加载图片简单用法
            Glide.with(context).load(Uri.parse((String) path)).into(imageView);
        }
    }
}
