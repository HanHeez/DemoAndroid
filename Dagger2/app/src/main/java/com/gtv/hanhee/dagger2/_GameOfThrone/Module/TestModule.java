package com.gtv.hanhee.dagger2._GameOfThrone.Module;

import com.gtv.hanhee.dagger2._GameOfThrone.Model.Cash;
import com.gtv.hanhee.dagger2._GameOfThrone.Model.Soldiers;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {
    Cash cash;

    @Provides
    Soldiers provideSoldiers() {
        return new Soldiers("Warrior");
    }
}
