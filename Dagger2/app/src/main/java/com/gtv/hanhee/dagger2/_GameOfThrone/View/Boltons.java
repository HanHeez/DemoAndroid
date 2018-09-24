package com.gtv.hanhee.dagger2._GameOfThrone.View;

import android.util.Log;

import com.gtv.hanhee.dagger2._GameOfThrone.Interface.House;

import javax.inject.Inject;

public class Boltons implements House {

    @Inject
    public Boltons() {

    }


    @Override
    public void prepareForWar() {
        //do something
        Log.d("checkwar"," Boltons prepared for war");
    }


    @Override
    public void reportForWar() {
        //do something
        Log.d("checkwar"," Boltons reporting");
    }
}
