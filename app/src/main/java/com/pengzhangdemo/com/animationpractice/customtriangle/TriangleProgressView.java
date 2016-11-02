package com.pengzhangdemo.com.animationpractice.customtriangle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2016/11/1.
 * User: Nick
 * Date: 2016/11/1
 * Email: 305812387@qq.com
 * Project: DrawDemo
 */

public class TriangleProgressView extends View implements View.OnClickListener {

    private int mWidthPixels;
    private int mHeightPixels;
    private float mScaledDensity;
    private Paint mPaint;
    private Paint paint2;
    private float mR;

    public TriangleProgressView(Context context) {
        this(context, null);
    }

    public TriangleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TriangleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);

        setOnClickListener(this);
        mWidthPixels = context.getResources().getDisplayMetrics().widthPixels;
        mHeightPixels = context.getResources().getDisplayMetrics().heightPixels;
        mScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        mR = mScaledDensity * 50;

    }

    private float offset = mR;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        polygon(path, 3, mWidthPixels / 2, mHeightPixels / 2, mR, 0, true);
        mPaint.setColor(Color.RED);
        canvas.drawPath(path, mPaint);
        Path path1 = new Path();
        paint2.setColor(Color.WHITE);
        polygon(path1, 3, mWidthPixels / 2, mHeightPixels / 2, offset, 0, true);
        canvas.drawPath(path1, paint2);
    }

    /**
     * @param path             路径
     * @param n                n条边
     * @param x                圆心 x
     * @param y                圆心 y
     * @param r                圆 半径
     * @param angle            初识角度
     * @param counterclockwise 顺逆时针
     */
    public void polygon(Path path, int n, float x, float y, float r, float angle, boolean counterclockwise) {
        y = y - (mR - r);//小三角形上移距离
        path.moveTo((float) (x + r * Math.sin(angle)), (float) (y - r * Math.cos(angle)));     //确立第一个点
        float delta = (float) (2 * Math.PI / n);        //相邻两个顶点之间的夹角
        for (int i = 0; i < n; i++) {            //其他顶点
            angle += counterclockwise ? -delta : delta;     //角度调整
            path.lineTo((float) (x + r * Math.sin(angle)), (float) (y - r * Math.cos(angle)));
        }
        path.close();     //首位相邻

    }

    @Override
    public void onClick(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(mR, 0);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
