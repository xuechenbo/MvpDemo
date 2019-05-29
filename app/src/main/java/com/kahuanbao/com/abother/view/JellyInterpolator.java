package com.kahuanbao.com.abother.view;

import android.animation.TimeInterpolator;

/**
 * Created by Administrator on 2019/2/14.
 *
 */

public class JellyInterpolator implements TimeInterpolator {

    // 因子数值越小振动频率越高
    private float factor;

    public JellyInterpolator() {
        this.factor = 2.0f;


    }

    @Override
    public float getInterpolation(float x) {
        //return (float)(Math.pow(2, -10 * x) * Math.sin((x - factor / 4) * (2 * Math.PI ) / factor) + 1);
        //return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
        return (float)(x * x * ((factor + 1) * x - factor));
    }
}
