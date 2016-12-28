package com.showjoy.image;

import android.content.Context;
import android.util.AttributeSet;

import com.showjoy.image.fresco.FrescoGifImageView;

/**
 * Gif图片
 * Created by lufei on 4/1/16.
 */
@Deprecated
public class SHGifImageView extends FrescoGifImageView {
    public SHGifImageView(Context context) {
        super(context);
    }

    public SHGifImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SHGifImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SHGifImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
