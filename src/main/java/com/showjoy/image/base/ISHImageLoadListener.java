package com.showjoy.image.base;

/**
 * Created by lufei on 6/20/16.
 */
public interface ISHImageLoadListener {

    void onSuccess(String id, int width, int height);

    void onFailure(String id, Throwable throwable);
}
