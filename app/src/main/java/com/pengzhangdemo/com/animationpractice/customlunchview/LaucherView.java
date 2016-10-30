package com.pengzhangdemo.com.animationpractice.customlunchview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pengzhangdemo.com.animationpractice.R;

/**
 * Created by zp on 10/30/16.
 */

public class LaucherView extends RelativeLayout {

    private int mHeight;
    private int mWidth;


    private int dp80 = Utils.dp2px(getContext(), 80);
    private boolean mHasStart;


    public LaucherView(Context context) {
        super(context);
        initView();
    }


    public LaucherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    public LaucherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    ImageView red, purple, yellow, blue;

    private void initView() {

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_HORIZONTAL, TRUE);//这里的TRUE 要注意 不是true
        lp.addRule(CENTER_VERTICAL, TRUE);

//        lp.setMargins(0, 0, 0, dp80);
//        purple = new ImageView(getContext());
//        purple.setLayoutParams(lp);
//        purple.setImageResource(R.drawable.shape_circle_purple);
//        addView(purple);
//
//        yellow = new ImageView(getContext());
//        yellow.setLayoutParams(lp);
//        yellow.setImageResource(R.drawable.shape_circle_yellow);
//        addView(yellow);
//
//        blue = new ImageView(getContext());
//        blue.setLayoutParams(lp);
//        blue.setImageResource(R.drawable.shape_circle_blue);
//        addView(blue);

        red = new ImageView(getContext());
        red.setLayoutParams(lp);
        red.setImageResource(R.drawable.shape_circle_red);
        addView(red);
    }

    /**
     * 当view为可见的时候，会调用这个方法
     *
     * @param hasWindowFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && !mHasStart) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                    mHasStart = true;
                }
            }, 500);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    public void start() {


        //路径1
        ViewPath redPath1 = new ViewPath(); //偏移坐标
        redPath1.moveTo(0, 0);
        redPath1.lineTo(mWidth / 5 - mWidth / 2, 0);



        ObjectAnimator redAnim1 = ObjectAnimator.ofObject(red, "fabLoc", new ViewPathEvaluator(), redPath1.getPoints().toArray());
        redAnim1.setInterpolator(new AccelerateDecelerateInterpolator());
        redAnim1.setDuration(2000);

        redAnim1.start();




    }


    private void setAnimation(final ImageView target, ViewPath path1, ViewPath path2) {
        //左右平移
        ObjectAnimator redAnim1 = ObjectAnimator.ofObject(new ViewObj(target), "fabLoc", new ViewPathEvaluator(), path1.getPoints().toArray());
        redAnim1.setInterpolator(new AccelerateDecelerateInterpolator());
        redAnim1.setDuration(800);
        //贝塞尔曲线
        ObjectAnimator redAnim2 = ObjectAnimator.ofObject(new ViewObj(target), "fabLoc", new ViewPathEvaluator(), path2.getPoints().toArray());
        redAnim2.setInterpolator(new AccelerateDecelerateInterpolator());


        //组合动画
//        addAnimation(redAnim1, redAnim2, target);
    }

    private class AnimEndListener extends AnimatorListenerAdapter {
        private View target;

        public AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView((target));
        }
    }

    public class ViewObj {

        private final ImageView red;

        public ViewObj(ImageView red) {
            this.red = red;
        }

        public void setFabLoc(ViewPoint newLoc) {
            red.setTranslationX(newLoc.x);
            red.setTranslationY(newLoc.y);
        }
    }


}
