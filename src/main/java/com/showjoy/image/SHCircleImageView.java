package com.showjoy.image;

import android.content.Context;
import android.util.AttributeSet;

import com.showjoy.image.fresco.FrescoCircleImageView;

/**
 * 圆圈的imageview
 * Created by lufei on 4/1/16.
 */
public class SHCircleImageView extends FrescoCircleImageView {

    public SHCircleImageView(Context context) {
        super(context);
        init();
    }

    public SHCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SHCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SHCircleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public void setBorderColor(int color) {
        super.setBorderColor(color);
    }

    @Override
    public void setBorderWidth(int width) {
        super.setBorderWidth(width);
    }
}
