package com.androidworld.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;
import com.androidworld.app.ui.fragment.InformationFragment;

import butterknife.Bind;

/**
 * <h3>主界面</h3>
 * <p>负责设置侧滑菜单以及主页面内容页切换</p>
 *
 * @author LQC
 *         当前时间：2016/6/4 20:28
 */
@SuppressWarnings("all")
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.vp_content)
    ViewPager vpContent;

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    private final String[] TITLES = {"Activity", "Fragment", "Service", "Broadcast", ""};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDrawerLayout();
        setTabLinkToViewPager();
    }

    /**
     * 初始化侧滑菜单
     */
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
            //  默认停留在主页
            mNavigationView.setCheckedItem(R.id.home);
        }
    }

    /**
     * 设置TabLayout与ViewPager的关联，并设置所需数据
     */
    private void setTabLinkToViewPager() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < TITLES.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(TITLES[i]));
        }
        vpContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(vpContent);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("Android World");
        super.initToolbar(toolbar);
    }

    @Override
    protected int getSubContentViewLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

            case R.id.message:

                break;

            case R.id.settings:
                startActivity(SettingsActivity.class);
                break;

            case R.id.nav_share:

                break;

            case R.id.nav_view:

                break;

            default:
                break;
        }

        return true;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("category", position);
            return Fragment.instantiate(//单例模式，防止重复创建
                    mContext, InformationFragment.class.getName(), bundle);
        }
    }
}
