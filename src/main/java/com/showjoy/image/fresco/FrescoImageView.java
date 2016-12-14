package com.showjoy.image.fresco;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.showjoy.image.base.ISHImageLoadListener;
import com.showjoy.image.base.ISHImageView;

import okhttp3.OkHttpClient;

/**
 * 尚妆统一的ImageView
 * Created by lufei on 3/30/16.
 */
public class FrescoImageView extends com.facebook.drawee.view.SimpleDraweeView implements ISHImageView {

    GenericDraweeHierarchyBuilder builder;
    GenericDraweeHierarchy hierarchy;
    ISHImageLoadListener loadListener;
    boolean enableWrapContent = false;
    boolean png2jpg = false;

    static CompressCallback sCompressCallback;

    String imageUrl;
    int imageRes;

    public FrescoImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public FrescoImageView(Context context) {
        super(context);
    }

    public FrescoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrescoImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FrescoImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void init() {
        hierarchy = getHierarchy();
        if (null == hierarchy) {
            builder = new GenericDraweeHierarchyBuilder(getResources());
            hierarchy = builder.build();
            setHierarchy(hierarchy);
        }
    }

    @Override
    public void setImageUrl(String url) {
        setImageUrl(url, true);
    }

    @Override
    public void setImageUrl(String url, boolean compressed) {
        if (compressed) {
            url = getCompressUrl(url);
        }

        if (TextUtils.isEmpty(url)) {
            return;
        }


        ViewGroup.LayoutParams params = getLayoutParams();
        if (null != params) {
            int width = params.width;
            int height = params.height;
            if (width > 0 && height > 0) {
                setImageUrl(url, width, height);
                return;
            }
        }

        if (url.equals(imageUrl)) {
            return;
        }
        imageUrl = url;

        Uri uri = Uri.parse(url);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(controllerListener)
                .build();
        setController(controller);

    }

    @Override
    public void setImageUrl(String url, ISHImageLoadListener loadListener) {
        this.loadListener = loadListener;
        setImageUrl(url);
    }

    @Override
    public void setImageUrl(String url, int width, int height) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        if (url.equals(imageUrl)) {
            return;
        }
        imageUrl = url;

        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .setPostprocessor(postprocessor)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setControllerListener(controllerListener)
                .setOldController(getController())
                .build();
        setController(controller);
    }

    @Override
    public void setImageUrl(String url, int width, int height, ISHImageLoadListener loadListener) {
        this.loadListener = loadListener;
        setImageUrl(url, width, height);
    }

    @Override
    public void setFailureImage() {

    }
    @Override
    public void placeholderImage(int resource) {
        hierarchy.setPlaceholderImage(resource);
    }
    @Override
    public void placeholderImage(Drawable drawable) {
        hierarchy.setPlaceholderImage(drawable);
    }

    @Override
    public void setImageRes(int resId) {
        imageRes = resId;
        setImageUrl("res://" + getContext().getPackageName() +  "/" + resId);
    }

    @Override
    public void setImageRes(int resId, ISHImageLoadListener loadListener) {
        this.loadListener = loadListener;
        setImageRes(resId);
    }

    @Override
    public void setWrapContentEnable(boolean enable) {
        enableWrapContent = enable;
    }

    @Override
    public String getCompressUrl(String url) {
        if (null != sCompressCallback) {
            ViewGroup.LayoutParams params = getLayoutParams();
            if (null != params) {
                return sCompressCallback.getCompressedUrl(url, params.width, params.height, png2jpg);
            }
            return sCompressCallback.getCompressedUrl(url, png2jpg);
        }
        return url;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int getImageRes() {
        return imageRes;
    }

    public void setCompressPng2Jpg(boolean png2jpg) {
        this.png2jpg = png2jpg;
    }

    public static void clearMemoryCaches() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
    }

    public static void clearDiskCaches() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearDiskCaches();
    }

    public static void clearCaches() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
    }

    public static void init(Context context, OkHttpClient okHttpClient) {
        if (null == okHttpClient) {
            ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                    .setDownsampleEnabled(true).build();
            Fresco.initialize(context, config);
        }else {
            ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                    .newBuilder(context, okHttpClient)
                    .setDownsampleEnabled(true)
                    .build();
            Fresco.initialize(context, config);
        }
    }

    public static void setCompressCallback(CompressCallback compressCallback) {
        sCompressCallback = compressCallback;
    }

    ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onFinalImageSet(
                String id,
                @Nullable ImageInfo imageInfo,
                @Nullable Animatable anim) {
            if (imageInfo == null) {
                if (null != loadListener) {
                    loadListener.onSuccess(id, 0, 0);
                }
                return;
            }
            if (null != loadListener) {
                loadListener.onSuccess(id, imageInfo.getWidth(), imageInfo.getHeight());
            }
            if (enableWrapContent) {
                setAspectRatio((float) imageInfo.getWidth() / imageInfo.getHeight());
            }
        }

        @Override
        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            FLog.d("FrescoImageView", "Intermediate image received");
        }

        @Override
        public void onFailure(String id, Throwable throwable) {
            FLog.e(getClass(), throwable, "Error loading %s", id);
            if (null != loadListener) {
                loadListener.onFailure(id, throwable);
            }
        }
    };

    Postprocessor postprocessor = new BasePostprocessor() {
        @Override
        public void process(Bitmap bitmap) {
            if (null != bitmap) {
                bitmap.setHasAlpha(true);
            }
        }
    } ;
}
