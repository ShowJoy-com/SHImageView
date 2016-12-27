# SHImageView

Android 简便的图片库，封装了fresco,非常方便使用，支持本地图片，网络图片，支持webp,支持圆形图。

## gradle 依赖

    compile 'com.showjoy.android:image:1.0.1'

【更新记录】

1. 2016.12.13    1.0.0   

   第一个版本，已经过产品的多次迭代，首次开源。
   
2. 2016.12.14    1.0.1    

   解决重复设置同个url闪烁的问题。提供获取已设置的url和res的接口
   public int getImageRes()
   public String getImageUrl()

【使用简介】

## 首先在application的onCreate里调用


    public class MainApplication extends Application {
       @Override
       public void onCreate() {
          super.onCreate();

          SHImageView.init(this);
        }
    }
    
## 使用简单示例

xml使用：

    <com.showjoy.image.SHImageView
        android:id="@+id/demo_image_view"
        android:layout_width="150dp"
        android:layout_height="150dp" />
        
        
    SHImageView imageView = (SHImageView) findViewById(R.id.demo_image_view);
    
    
直接java代码里动态生成：

    imageView = new SHImageView(context);
    
    
    //网络图片
    imageView.setImageUrl("http://t.cn/R5JfqHu");
    //本地图片
    //imageView.setImageUrl("file://sdcard/sample/xxx.jpg");
        
    //在setImageUrl之前调用
    imageView.setWrapContentEnable(true);//支持自适应宽高
    //可以注入压缩接口
    imageView.setCompressCallback(CompressCallback compressCallback);

### SHImageView   普通图片

    //直接设置url，其他都不用管
    public void setImageUrl(String url);

    //直接设置res id，其他都不用管
    public void setImageRes(int resId);

    //静态方法，用来手动清除内存里的缓存
    public static void clearMemoryCaches();

    //静态方法，用来手动清除文件里的缓存
    public static void clearDiskCaches();
    
    //静态方法，用来手动清除所有缓存
    public static void clearCaches();

    //静态方法，初始化，放在application的oncreate里 
    public static void init(Context context);

### SHCircleImageView  圆形图片

包含SHImageView的接口，另外包含以下接口
    
    //设置边框颜色
    void setBorderColor(int color);
    
    //设置边框宽度
    void setBorderWidth(int width);
    

### SHGifImageView  动态图

  包含SHImageView的接口, 使用方法一致
  

### 混淆配置

    -keep class com.facebook.imagepipeline.gif.** { *; }
    -keep class com.facebook.imagepipeline.webp.** { *; }
    -keep @com.facebook.common.internal.DoNotStrip class *
    -keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
    }
    -keepclassmembers class * {
    native <methods>;
    }

麻烦接入方在这里登记一下， 谢谢
https://github.com/ShowJoy-com/SHImageView/issues/1
