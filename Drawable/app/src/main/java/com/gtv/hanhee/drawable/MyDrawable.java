package com.gtv.hanhee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyDrawable extends Drawable {

    private final Paint mRedPaint;

    public MyDrawable() {
        // Set up color and text size
        mRedPaint = new Paint();
        mRedPaint.setARGB(255, 255, 0, 0);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();
        float radius = Math.min(width, height) / 2;

        // Draw a red circle in the center
        canvas.drawCircle(width/2, height/2, radius, mRedPaint);

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
