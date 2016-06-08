package com.androidworld.app.util;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * 动画工具
 * @author 小秋秋
 *
 */
public class ToolAnimation {

	/**
	 * 呼吸效果
	 * 
	 */
	public static void breathe(View target, float from, float to,int targetY) {
		float x = target.getX();
		float y = target.getY();
		ObjectAnimator revealAnimatorx = ObjectAnimator.ofFloat( // 缩放X 轴的
				target, "scaleX", from, to);
		revealAnimatorx.setRepeatCount(Integer.MAX_VALUE);
		revealAnimatorx.setRepeatMode(Animation.REVERSE);

		ObjectAnimator revealAnimatory = ObjectAnimator.ofFloat(// 缩放Y 轴的
				target, "scaleY", from, to);
		revealAnimatory.setRepeatCount(Integer.MAX_VALUE);
		revealAnimatory.setRepeatMode(Animation.REVERSE);

		ObjectAnimator revealAnimator2 = ObjectAnimator.ofFloat(// 移动Y 轴的
				target, "y", y, y - targetY);
		revealAnimator2.setRepeatCount(Integer.MAX_VALUE);
		revealAnimator2.setRepeatMode(Animation.REVERSE);
//
//		ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(target, "backgroundColor", new ArgbEvaluator(),
//				colorTo);
//		backgroundColorAnimator.setRepeatCount(Integer.MAX_VALUE);
//		backgroundColorAnimator.setRepeatMode(Animation.REVERSE);

		AnimatorSet set = new AnimatorSet();
		set.setDuration(1500);// 设置播放时间
		// 播放速度的模式,等待设置
		set.setInterpolator(new AccelerateDecelerateInterpolator());// 设置播放模式，
		set.playTogether(revealAnimatorx, revealAnimatory, revealAnimator2);// 设置一起播放
		set.start();
	}

	/**
	 * 自定义Height属性动画
	 * 
	 * @param params
	 * @param listener
	 * @param valueTo
	 */
	public static void bounceY(View params, String tag, int time, int count, final MyValueAnimation listener,
			int... valueTo) {
		if (time <= 0) {
			time = 1;
		}
		ObjectAnimator revealAnimatory = ObjectAnimator.ofInt(params, tag, valueTo);
		revealAnimatory.setRepeatCount(count - 1);
		revealAnimatory.setRepeatMode(Animation.REVERSE);
		revealAnimatory.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int value = (Integer) animation.getAnimatedValue();
				if (value > 1) {
					if (listener != null) {
						listener.update(value);
					}
				}

			}
		});
		AnimatorSet set = new AnimatorSet();
		set.setDuration(time);// 设置播放时间
		// 播放速度的模式,等待设置
		set.setInterpolator(new BounceInterpolator());// 设置播放模式，
		set.playTogether(revealAnimatory);// 设置一起播放

		set.start();
	}

	/**
	 * 弹跳效果
	 * 
	 * @param target
	 * @param count
	 * @param listener
	 */
	public static void bounce(View target, int count, final MyAnimationListener2 listener) {
		float x = target.getX();
		float y = target.getY();

		ObjectAnimator revealAnimator1 = ObjectAnimator.ofFloat(// 移动Y 轴的
				target, "y", y, y - 80);
		revealAnimator1.setRepeatCount(count);
		revealAnimator1.setRepeatMode(Animation.REVERSE);
		revealAnimator1.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (listener != null) {
					listener.end();
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}
		});

		AnimatorSet set = new AnimatorSet();
		set.setDuration(800);// 设置播放时间
		// 播放速度的模式,等待设置
		set.setInterpolator(new BounceInterpolator());// 设置播放模式，
		set.playTogether(revealAnimator1);// 设置一起播放
		set.start();
	}

	/**
	 * 缩放动画
	 * 
	 * @param oval
	 * @param delay
	 */
	public static void ZoomAnimation(View oval, int delay) {
		// scaleX View对象的一个属性,该类必须提供一个setScaleX方法,才能用
		ObjectAnimator revealAnimator = ObjectAnimator.ofFloat( // 缩放X 轴的
				oval, "scaleX", 0, 200);
		ObjectAnimator revealAnimator1 = ObjectAnimator.ofFloat(// 缩放Y 轴的
				oval, "scaleY", 0, 200);
		AnimatorSet set = new AnimatorSet();
		set.setDuration(delay);// 设置播放时间
		// 播放速度的模式,等待设置
		set.setInterpolator(new LinearInterpolator());// 设置播放模式，这里是平常模式
		set.playTogether(revealAnimator, revealAnimator1);// 设置一起播放
		set.start();
	}

	/**
	 * 设置View的背景动画
	 * 
	 * @param view
	 * @param delay
	 *            延时
	 * @param listener
	 *            更新回调
	 * @param colorTo
	 *            变化的颜色组
	 */
	public static void colorShade(View view, int delay, final MyAnimationListener listener, final Object... colorTo) {
		// 颜色变化
		ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(),
				colorTo);
		backgroundColorAnimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (listener != null) {
					listener.update((Integer)colorTo[0], (Integer) animation.getAnimatedValue(), (Integer)colorTo[colorTo.length - 1]);
				}
			}
		});
		AnimatorSet set = new AnimatorSet();
		set.setDuration(delay);// 设置播放时间
		// 播放速度的模式,等待设置
		set.setInterpolator(new MyInterpolator(1));// 设置播放模式，这里是平常模式
		set.playTogether(backgroundColorAnimator);// 可以增加其他动画设置一起播放
		set.start();
	}

	/**
	 * i = 1 的时候是匀速, i = 2 的时候3次方曲线加速 其他官方提供的效果 AccelerateInterpolator
	 * 加速，开始时慢中间加速 DecelerateInterpolator 减速，开始时快然后减速
	 * AccelerateDecelerateInterpolator 先加速后减速，开始结束时慢，中间加速
	 * AnticipateInterpolator 反向 ，先向相反方向改变一段再加速播放
	 * AnticipateOvershootInterpolator 反向加回弹，先向相反方向改变，再加速播放，会超出目的值然后缓慢移动至目的值
	 * BounceInterpolator 跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100
	 * CycleIinterpolator 循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2 * mCycles * Math.PI
	 * * input) LinearInterpolator 线性，线性均匀改变 OvershottInterpolator
	 * 回弹，最后超出目的值然后缓慢改变到目的值 TimeInterpolator
	 * 一个接口，允许你自定义interpolator，以上几个都是实现了这个接口
	 * 
	 */
	public static class MyInterpolator implements TimeInterpolator {
		private float mFactor;
		private int i;

		public MyInterpolator(int i) {
			this.i = i;
		}

		@Override
		public float getInterpolation(float input) {
			switch (i) {
			case 1:
				mFactor = input;
				break;
			case 2:
				mFactor = input * input * input;
				break;
			}
			return mFactor;

		}
	}

	public interface MyAnimationListener {
		/**
		 * 更新回调
		 * 
		 */
		void update(int startColor, int currentColor, int endColor);

	}

	public interface MyValueAnimation {
		/**
		 * 更新回调
		 * 
		 */
		void update(int value);

	}

	public interface MyAnimationListener2 {
		void end();
	}
}
