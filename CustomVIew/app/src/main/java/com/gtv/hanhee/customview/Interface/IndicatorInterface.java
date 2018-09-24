package com.gtv.hanhee.customview.Interface;

import android.support.v4.view.ViewPager;

public interface IndicatorInterface {

    void setViewPager(ViewPager viewPager) throws PageLessException;

    void setAnimateDuration(long duration);

    /**
     *
     * @param radius: radius in pixel
     */
    void setRadiusSelected(int radius);

    /**
     *
     * @param radius: radius in pixel
     */
    void setRadiusUnselected(int radius);

    /**
     *
     * @param distance: distance in pixel
     */
    void setDistanceDot(int distance);
}
