package com.gtv.hanhee.ungdungquanan.View.ManHinhTrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gtv.hanhee.ungdungquanan.Adapter.AdapterViewPagerTrangchu;
import com.gtv.hanhee.ungdungquanan.R;

public class TrangChuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    RadioButton rdOdau, rdAngi;
    RadioGroup radioGroup;

    ViewPager viewPagerTrangchu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_trangchu);

        viewPagerTrangchu = (ViewPager) findViewById(R.id.viewPagerTrangchu);
        rdOdau = (RadioButton) findViewById(R.id.rdOdau);
        rdAngi = (RadioButton) findViewById(R.id.rdAngi);
        radioGroup = (RadioGroup) findViewById(R.id.rdGroupToolbar);

        AdapterViewPagerTrangchu adapterViewPagerTrangchu = new AdapterViewPagerTrangchu(getSupportFragmentManager());

        viewPagerTrangchu.setAdapter(adapterViewPagerTrangchu);
        viewPagerTrangchu.addOnPageChangeListener(this);
        radioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                rdOdau.setChecked(true);
                break;
            case 1:
                rdAngi.setChecked(true);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rdOdau:
                viewPagerTrangchu.setCurrentItem(0);
                break;
            case R.id.rdAngi:
                viewPagerTrangchu.setCurrentItem(1);
                break;
        }

    }
}
