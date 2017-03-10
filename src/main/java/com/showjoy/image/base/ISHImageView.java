package com.showjoy.image.base;

import android.graphics.drawable.Drawable;

/**
 * Created by lufei on 3/31/16.
 */
public interface ISHImageView {

    void setImageUrl(String url);

    void setImageUrl(String url, boolean compressed);

    void setImageUrl(String url, ISHImageLoadListener loadListener);

    void setImageUrl(String url, int width, int height);

    void setImageUrl(String url, int width, int height, ISHImageLoadListener loadListener);

    void setFailureImage();

    void placeholderImage(int resource);

    void placeholderImage(Drawable drawable);

    void setImageRes(int resId);

    void setImageRes(int resId, ISHImageLoadListener loadListener);

    /**
     * 设置图片自适应宽高
     * 适用场景：宽和高 都可以是 WRAP_CONTENT，但是不能为 0
     *
     * @param enable 是否自适应
     */
    void setWrapContentEnable(boolean enable);

    String getCompressUrl(String url);

    String getImageUrl();
    int getImageRes();

    void setBorderRadius(float[] borderRadius);

    interface CompressCallback {
        String getCompressedUrl(String url, int width, int height, boolean png2jpg);
        String getCompressedUrl(String url, boolean png2jpg);
    }
}
