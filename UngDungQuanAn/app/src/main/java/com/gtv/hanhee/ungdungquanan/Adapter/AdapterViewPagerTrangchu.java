package com.gtv.hanhee.ungdungquanan.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gtv.hanhee.ungdungquanan.View.Fragments.AngiFragment;
import com.gtv.hanhee.ungdungquanan.View.Fragments.OdauFragment;

public class AdapterViewPagerTrangchu extends FragmentStatePagerAdapter {

    AngiFragment angiFragment;
    OdauFragment odauFragment;

    public AdapterViewPagerTrangchu(FragmentManager fm) {
        super(fm);
        angiFragment = new AngiFragment();
        odauFragment = new OdauFragment();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return odauFragment;
            case 1:
                return angiFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
