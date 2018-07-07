package com.products.ammar.sheikhsha3ban.common;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RatingBar;

import com.appyvet.materialrangebar.RangeBar;

public class VerticalRatingBar extends RangeBar {
    private int x, y, z, w;

    @Override
    protected void drawableStateChanged() {
        // TODO Auto-generated method stub
        super.drawableStateChanged();
    }

    public VerticalRatingBar(Context context) {
        super(context);
    }

    public VerticalRatingBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
        this.x = w;
        this.y = h;
        this.z = oldw;
        this.w = oldh;
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec,
                                          int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {
        c.rotate(-90);
        c.translate(-getHeight(), 0);
        super.onDraw(c);
    }

    @Override
    public void setRangePinsByIndices(int leftPinIndex, int rightPinIndex) {
        super.setRangePinsByIndices(leftPinIndex, rightPinIndex);
        onSizeChanged(x, y, z, w);

    }

    @Override
    public void setSeekPinByIndex(int pinIndex) {
        super.setSeekPinByIndex(pinIndex);
        onSizeChanged(x, y, z, w);

    }

    @Override
    public void setRangePinsByValue(float leftPinValue, float rightPinValue) {
        super.setRangePinsByValue(leftPinValue, rightPinValue);
        onSizeChanged(x, y, z, w);

    }

    @Override
    public void setSeekPinByValue(float pinValue) {
        super.setSeekPinByValue(pinValue);
        onSizeChanged(x, y, z, w);
    }
}