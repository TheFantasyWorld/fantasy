package com.androidworld.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.androidworld.app.R;
import com.androidworld.app.bean.Blog;
import com.androidworld.app.ui.activity.base.BaseActivity;
import com.androidworld.app.ui.activity.thread.ThreadActivity;
import com.androidworld.app.util.ExitAppUtil;
import com.androidworld.app.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.fattycat.kun.library.ImageChooserDialog;

import static com.androidworld.app.R.id.nav_view;

/**
 * <h3>主界面</h3>
 * <p>负责设置侧滑菜单以及主页面内容页切换</p>
 *
 * @author LQC
 *         当前时间：2016/6/4 20:28
 */
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Bind(nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.rv_blog)
    RecyclerView rvBlog;

    @Bind(R.id.vp_banner)
    ViewPager vpBanner;

    @Bind(R.id.et_search)
    EditText etSearch;

    ImageView ivUserAvatar;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_user_avatar:
                    showImageChooserDialog();
                    break;
            }
        }
    };

    /**
     * 裁剪照片结果
     */
    private static final int REQUEST_CROP_IMAGE = 0x01;
    private ImageChooserDialog mImageChooserDialog;
    private ExitAppUtil mExitAppUtil = new ExitAppUtil(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        initDrawerLayout();
        initData();
    }

    private void initData() {
        final ArrayList<View> viewList = new ArrayList<>();
        viewList.add(View.inflate(this, R.layout.pager_banner, null));
        viewList.add(View.inflate(this, R.layout.pager_banner, null));
        viewList.add(View.inflate(this, R.layout.pager_banner, null));
        vpBanner.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {//必须实现
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {//必须实现
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
                container.removeView(viewList.get(position));
            }
        });

        List<Blog> blogList = new ArrayList<>();
        blogList.add(new Blog("", "123", "", ""));
        blogList.add(new Blog("", "456", "", ""));
        blogList.add(new Blog("", "789", "", ""));
        blogList.add(new Blog("", "987", "", ""));
        blogList.add(new Blog("", "654", "", ""));
        blogList.add(new Blog("", "321", "", ""));
        rvBlog.setLayoutManager(new LinearLayoutManager(mContext));
        rvBlog.setAdapter(new BaseQuickAdapter<Blog, BaseViewHolder>(R.layout.item_blog, blogList) {
            @Override
            protected void convert(BaseViewHolder helper, Blog item) {
                helper.setText(R.id.tv_title, item.title);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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

    private void showImageChooserDialog() {
        if (mImageChooserDialog == null) {
            mImageChooserDialog = new ImageChooserDialog();
            mImageChooserDialog.setListener(new ImageChooserDialog.OnImageChooserListener() {
                @Override
                public void onDoneClicked(List<String> imagePaths) {
                    if (imagePaths != null && imagePaths.size() > 0) {
                        toCropImage(Uri.fromFile(new File(imagePaths.get(0))));
                    }
                }

                @Override
                public void onCameraCaptureSuccess(Bitmap photoBitmap) {
                    toCropImage(Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), photoBitmap, null, null)));
                }
            });
            mImageChooserDialog.setChooserType(ImageChooserDialog.CHOOSER_TYPE_SINGLE);
            mImageChooserDialog.setTitleText("选择图片");
            mImageChooserDialog.setDoneText("完成");
        }
        mImageChooserDialog.show(getSupportFragmentManager(), mImageChooserDialog.getTag());
    }

    private void toCropImage(Uri uri) {
        final Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "avatar.jpg"));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, REQUEST_CROP_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CROP_IMAGE) {
                Glide.with(mContext)
                        .load(data.getData())
                        .asBitmap()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(new BitmapImageViewTarget(ivUserAvatar) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                ivUserAvatar.setImageDrawable(circularBitmapDrawable);
                            }
                        });
                mImageChooserDialog.dismiss();
            }
        }
    }
}
