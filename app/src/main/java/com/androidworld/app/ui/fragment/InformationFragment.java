package com.androidworld.app.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidworld.app.R;
import com.androidworld.app.ui.activity.base.BaseActivity;
import com.androidworld.app.ui.fragment.base.BaseLazyLoadFragment;
import com.androidworld.app.util.DensityUtils;

import java.util.ArrayList;

/**
 * <h3>资讯</h3>
 *
 * @author LQC
 *         当前时间：2016/6/9 21:08
 */
public class InformationFragment extends BaseLazyLoadFragment {

    private ViewPager mViewPager;
    private LinearLayout pointGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        init(view);
//        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                getActivity(),
//                new Pair<View, String>(view.findViewById(R.id.iv), "1"),
//                new Pair<View, String>(view.findViewById(R.id.iv), "2"));
//        ActivityCompat.startActivity(getActivity(), new Intent(getActivity(), LoginActivity.class), activityOptions.toBundle());
    }

    // 图片资源ID
    private final int[] imageIds = {
            R.mipmap.header,
            R.mipmap.login_bg,
            R.mipmap.mine_bg,
            R.mipmap.welcome_top };
    private ArrayList<ImageView> imageList;

    public void init(View view) {

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        pointGroup = (LinearLayout) view.findViewById(R.id.point_group);

        imageList = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {

            // 初始化图片资源
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(imageIds[i]);
            imageList.add(image);

            // 添加指示点
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtils.dp2px(getActivity(), 6f),
                    DensityUtils.dp2px(getActivity(), 6f));

            params.rightMargin = 12;
            point.setLayoutParams(params);

            if (i == 0) {
                point.setImageBitmap(getCircle(6, ((BaseActivity) getActivity()).getColorPrimary(), getActivity()));
            } else {
                point.setImageBitmap(getCircle(6, 0x55000000, getActivity()));
            }
            pointGroup.addView(point);
        }

        mViewPager.setAdapter(new MyPagerAdapter());

        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imageList.size()));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            /**
             * 页面切换后调用
             * position  新的页面位置
             */
            public void onPageSelected(int position) {

                position = position % imageList.size();

                // 改变指示点的状态
                ((ImageView)pointGroup.getChildAt(position)).setImageBitmap(getCircle(6,((BaseActivity) getActivity()).getColorPrimary(), getActivity()));
                // 把上一个点设为false
                ((ImageView)pointGroup.getChildAt(lastPosition)).setImageBitmap(getCircle(6, 0x55000000, getActivity()));
                lastPosition = position;

            }

            /**
             * 页面正在滑动的时候，回调
             */
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            /**
             * 当页面状态发生变化的时候，回调
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            /*
//             * 自动循环： 1、定时器：Timer 2、开子线程 while true 循环 3、ColckManager 4、 用handler
//		     * 发送延时信息，实现循环
//		     */
//            handler.sendEmptyMessageDelayed(0, autoScrollTime);
//            isAutoScroll = true;
//        } else {
//            isAutoScroll = false;
//        }
//    }

    /**
     * 上一个页面的位置
     */
    protected int lastPosition;

    /**
     * 是否自动滚动
     */
    private boolean isAutoScroll = false;

    private int autoScrollTime = 3000;

    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            // 让viewPager 滑动到下一页
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            if (isAutoScroll) {
                handler.sendEmptyMessageDelayed(0, autoScrollTime);
            }
        }
    };

    private class MyPagerAdapter extends PagerAdapter {

        /**
         * 获得页面的总数
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * 获得相应位置上的view container view的容器，其实就是viewpager自身 position 相应的位置
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // System.out.println("instantiateItem  ::" + position);
            imageList.get(position % imageList.size()).setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                        }
                    });
            // 给 container 添加一个view
            container.addView(imageList.get(position % imageList.size()));
            // 返回一个和该view相对的object
            return imageList.get(position % imageList.size());
        }

        /**
         * 判断 view和object的对应关系
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 销毁对应位置上的object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // System.out.println("destroyItem  ::" + position);

            container.removeView((View) object);
            object = null;
        }
    }

    /**
     * 画一个圆形
     */
    public static Bitmap getCircle(int rdp, int color, Context context){
        int r = DensityUtils.dp2px(context, rdp);
        Bitmap resultBitmap = Bitmap.createBitmap(r*2, r*2, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(resultBitmap);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(color);// 设置透明度
        mPaint.setAlpha(color>>24 & 0xff);
        mCanvas.drawCircle(r, r, r, mPaint);
        //mCanvas.drawRect(mIconRect, mPaint);
        return resultBitmap;

    }
}
