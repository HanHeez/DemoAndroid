package com.gtv.hanhee.dagger2._GameOfThrone.View;

import android.util.Log;

import com.gtv.hanhee.dagger2._GameOfThrone.Interface.House;
import com.gtv.hanhee.dagger2._GameOfThrone.Model.Allies;

import javax.inject.Inject;

public class Starks implements House {

    @Inject
    public Starks() {

    }

    @Override
    public void prepareForWar() {
        //do something
        Log.d("checkwar", "Starks prepare War");
    }

    @Override
    public void reportForWar() {
        //do something
        Log.d("checkwar", "Starks reporting...");
    }
}
