package com.showjoy.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.showjoy.image.base.ISHImageLoadListener;
import com.showjoy.image.fresco.FrescoImageView;

/**
 * 尚妆统一图片组件
 * Created by lufei on 3/30/16.
 */
public class SHImageView extends FrescoImageView {

    static CompressCallback sCompressCallback;

    public SHImageView(Context context) {
        super(context);
        init();
    }

    public SHImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SHImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SHImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public void setImageUrl(String url) {
        super.setImageUrl(url, true);
    }

    @Override
    public void setImageUrl(String url, boolean compressed) {
        super.setImageUrl(url, compressed);
    }

    @Override
    public void setFailureImage() {
        super.setFailureImage();
    }

    @Override
    public void placeholderImage(int resource) {
        super.placeholderImage(resource);
    }

    @Override
    public void placeholderImage(Drawable drawable) {
        super.placeholderImage(drawable);
    }

    @Override
    public void setImageRes(int resId) {
        super.setImageRes(resId);
    }

    @Override
    public void setImageUrl(String url, ISHImageLoadListener loadListener) {
        super.setImageUrl(url, loadListener);
    }

    @Override
    public void setImageUrl(String url, int width, int height) {
        super.setImageUrl(url, width, height);
    }

    @Override
    public void setImageUrl(String url, int width, int height, ISHImageLoadListener loadListener) {
        super.setImageUrl(url, width, height, loadListener);
    }

    @Override
    public void setImageRes(int resId, ISHImageLoadListener loadListener) {
        super.setImageRes(resId, loadListener);
    }

    @Override
    public void setWrapContentEnable(boolean enable) {
        super.setWrapContentEnable(enable);
    }

    @Override
    public int getImageRes() {
        return super.getImageRes();
    }

    @Override
    public String getImageUrl() {
        return super.getImageUrl();
    }
}
