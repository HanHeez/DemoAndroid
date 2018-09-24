package com.gtv.hanhee.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    Button btnFragA, btnFragB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnFragA = (Button) findViewById(R.id.btnFragA);
//        btnFragB = (Button) findViewById(R.id.btnFragB);
//
//        FragmentManager fragmentManager = getFragmentManager();
//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//
//        btnFragA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentA fragmentA = new FragmentA();
//                fragmentTransaction.add(R.id.frameContent,fragmentA);
//                fragmentTransaction.commit();
//            }
//        });
//
//        btnFragB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentB fragmentB = new FragmentB();
//                fragmentTransaction.add(R.id.frameContent,fragmentB);
//                fragmentTransaction.commit();
//            }
//        });
    }

    public void AddFragment(View view) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.btnFragA:
                fragment = new FragmentA();
                break;
            case R.id.btnFragB:
                fragment = new FragmentB();
                break;
        }

        fragmentTransaction.replace(R.id.frameContent,fragment);
        fragmentTransaction.commit();
    }
}
