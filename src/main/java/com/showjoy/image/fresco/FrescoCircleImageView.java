package com.showjoy.image.fresco;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.generic.RoundingParams;
import com.showjoy.image.base.ISHCircleImageView;

/**
 * 圆圈的imageview
 * Created by lufei on 4/1/16.
 */
public class FrescoCircleImageView extends FrescoImageView implements ISHCircleImageView {

    public FrescoCircleImageView(Context context) {
        super(context);
    }

    public FrescoCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrescoCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FrescoCircleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init() {
        super.init();
        getHierarchy().setRoundingParams(getRoundingParams());
    }

    @Override
    public void setBorderColor(int color) {
        RoundingParams roundingParams = getRoundingParams();
        roundingParams.setBorder(color, roundingParams.getBorderWidth());
        getHierarchy().setRoundingParams(roundingParams);
    }

    @Override
    public void setBorderWidth(int width) {
        RoundingParams roundingParams = getRoundingParams();
        roundingParams.setBorder(roundingParams.getBorderColor(), width);
        getHierarchy().setRoundingParams(roundingParams);
    }

    protected RoundingParams getRoundingParams() {
        RoundingParams roundingParams = getHierarchy().getRoundingParams();
        if (null == roundingParams) {
            roundingParams = RoundingParams.asCircle();
        } else  {
            roundingParams.setRoundAsCircle(true);
        }
        return roundingParams;
    }
}
