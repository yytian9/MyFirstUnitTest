package com.example.yytian.simplemocktest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yytian.simplemocktest.R;


public class ProgressView extends LinearLayout {

    private TextView mTvNote;
    private ImageView mIvIcon;

    private boolean isProgressEnable = true;
    private long    mMax             = 100;
    private long mProgress;

    public void setIsProgressEnable(boolean isProgressEnable) {
        this.isProgressEnable = isProgressEnable;
    }

    public void setMax(long max) {
        mMax = max;
    }

    public void setProgress(long progress) {
        this.mProgress = progress;
        //重绘
        invalidate();
    }

    public void setNote(String note) {
        mTvNote.setText(note);
    }

    public void setIcon(int resId) {
        mIvIcon.setImageResource(resId);
    }

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.inflate_progressview, this);
        // 找到孩子对象
        mTvNote = (TextView) view.findViewById(R.id.tv_note);
        mIvIcon = (ImageView) view.findViewById(R.id.iv_Icon);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);//绘制背景-->透明的背景
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);//(绘制图标+文字)

        if (isProgressEnable) {
            float left = mIvIcon.getLeft();
            float top = mIvIcon.getTop();
            float right = mIvIcon.getRight();
            float bottom = mIvIcon.getBottom();
            //控制弧形绘制范围
            RectF oval = new RectF(left, top, right, bottom);
            //开始角度
            float startAngle = -90;
            //扫过的角度-->动态计算,动态修改

            float sweepAngle = mProgress * 360.f / mMax;

            boolean useCenter = false;

            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);

            paint.setStrokeWidth(3);

            //消除锯齿
            paint.setAntiAlias(true);
            canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint);
        }
    }
}
