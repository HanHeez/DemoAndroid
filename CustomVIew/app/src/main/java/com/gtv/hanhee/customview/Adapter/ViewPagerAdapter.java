package com.gtv.hanhee.customview.Adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.gtv.hanhee.customview.Fragment.FragmentA;
import com.gtv.hanhee.customview.Fragment.FragmentB;
import com.gtv.hanhee.customview.Fragment.FragmentC;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                FragmentA fragmentA = new FragmentA();
                return fragmentA;
            case 1:
                FragmentB fragmentB = new FragmentB();
                return fragmentB;
            case 2:
                FragmentC fragmentC = new FragmentC();
                return fragmentC;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng ký";
            case 2:
                return "Đăng xuất";
            default:
                return null;
        }
    }
}
