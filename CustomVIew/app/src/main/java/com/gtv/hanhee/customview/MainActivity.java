package com.gtv.hanhee.customview;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gtv.hanhee.customview.Adapter.ViewPagerAdapter;
import com.gtv.hanhee.customview.Interface.PageLessException;
import com.gtv.hanhee.customview.View.IndicatorView;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    IndicatorView indicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        pager = findViewById(R.id.viewPager);
        indicatorView = findViewById(R.id.indicator);
        FragmentManager manager = getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(manager);
        pager.setAdapter(adapter);
        try {
            indicatorView.setViewPager(pager);
        }catch (PageLessException e) {
            e.printStackTrace();
        }

    }
}
