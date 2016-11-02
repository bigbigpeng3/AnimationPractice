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
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/1.
 * User: Nick
 * Date: 2016/11/1
 * Email: 305812387@qq.com
 * Project: DrawDemo
 */

public class RightTriangleProgressView extends View implements View.OnClickListener {

    private int mWidthPixels;
    private int mHeightPixels;
    private float mScaledDensity;
    private Paint mPaint;
    private Paint mPaint2;
    private Paint mPaintBig;
    private Paint mPaintSmall;
    private float mR;

    public RightTriangleProgressView(Context context) {
        this(context, null);
    }

    public RightTriangleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RightTriangleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaintBig = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSmall = new Paint(Paint.ANTI_ALIAS_FLAG);

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

//        Path path = new Path();
//        polygon(path, 3, mWidthPixels / 2, mHeightPixels / 2, mR, 0, true);
//        mPaint.setColor(Color.RED);
//        canvas.drawPath(path, mPaint);
//
//        Path path1 = new Path();
//        mPaint2.setColor(Color.WHITE);
//        polygon(path1, 3, mWidthPixels / 2, mHeightPixels / 2, offset, 0, true);
//        canvas.drawPath(path1, mPaint2);



        Path bigTri = new Path();
        mPaintBig.setColor(Color.RED);
        mPaintBig.setStyle(Paint.Style.STROKE);
        mPaintBig.setStrokeWidth(2);
        drawBig(bigTri);
        canvas.drawPath(bigTri, mPaintBig);


        Path smallTri = new Path();
        mPaintSmall.setColor(Color.RED);
        mPaintSmall.setStyle(Paint.Style.FILL);
        drawsmall(smallTri);
        canvas.drawPath(smallTri, mPaintSmall);



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


    public void drawBig (Path path){

        path.moveTo(300,500);
        path.lineTo(0,500);
        path.lineTo(300,0);
        path.close();

    }

    private float max = 300;
    private float min = 0;

    public void drawsmall(Path path){

        path.moveTo(0,500);
        path.lineTo(min,500);
        path.lineTo(min,500 - (min * 5)/3);
        path.close();
    }

    @Override
    public void onClick(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, max);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                min = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
        Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
    }
}
