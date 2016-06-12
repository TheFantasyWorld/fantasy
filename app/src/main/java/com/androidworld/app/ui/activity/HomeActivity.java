package com.androidworld.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <h3>主界面</h3>
 * <p>负责设置侧滑菜单以及主页面内容页切换</p>
 * @author LQC
 *         当前时间：2016/6/4 20:28
 */
@SuppressWarnings("deprecation")
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initDrawerLayout();
    }

    /**
     * 初始化侧滑菜单
     */
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        super.initToolbar(toolbar);
        toolbar.setTitle("Android World");
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:

                break;

            case R.id.nav_gallery:

                break;

            case R.id.nav_manage:

                break;

            case R.id.nav_send:

                break;

            case R.id.nav_share:

                break;

            case R.id.nav_slideshow:

                break;

            case R.id.nav_view:

                break;

            default:
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
