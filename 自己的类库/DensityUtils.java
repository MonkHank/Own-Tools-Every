package com.seuic.zhbj.utils;

import android.content.Context;

/**
 * Created by bgl on 2017/5/30.
 */

public class DensityUtils {
    public static int dip2px(float dip, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f); // 四舍五入
        return px;
    }

    public static float px2dip(int px, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        float dip = px / density;
        return dip;
    }
}
