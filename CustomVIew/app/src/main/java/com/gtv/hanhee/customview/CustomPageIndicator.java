package com.gtv.hanhee.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class CustomPageIndicator extends View {
    private Bitmap mBitmap;
    int count;

    public CustomPageIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomPageIndicator);
        count = typedArray.getInt(R.styleable.CustomPageIndicator_cpi_count,1);
        loadBitmap();
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mBitmap == null) {
            return;
        }
        int desiredWidth = mBitmap.getWidth();
        int desiredHeight = mBitmap.getHeight();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = 20;
                break;
            case MeasureSpec.AT_MOST:
                width = Math.min(desiredWidth, widthSize);
                break;
            default:
                width = desiredWidth;
                break;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = 20;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    Paint paint = new Paint();
    DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth()/2;
        int y = getHeight()/2;
        int radius = y;

        paint.setColor(Color.parseColor("#f00000"));
        for (int i=0;i<count;i++) {
            canvas.drawCircle(x, y, radius, paint);
        }

        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setDuration(1000);
        animator.setInterpolator(decelerateInterpolator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int newRadius = (int) animation.getAnimatedValue();
            }
        });
        animator.start();
    }

    private void loadBitmap() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nikita);
    }
}
