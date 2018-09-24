package com.gtv.hanhee.dagger2._GameOfThrone.Module;

import com.gtv.hanhee.dagger2._GameOfThrone.Model.Cash;
import com.gtv.hanhee.dagger2._GameOfThrone.Model.Soldiers;

import dagger.Module;
import dagger.Provides;

@Module(includes = TestModule.class)
public class BraavosModule {
    Cash cash;
    Soldiers soldiers;

    public BraavosModule(Cash cash, Soldiers soldiers){
        this.cash=cash;
        this.soldiers=soldiers;
    }

    @Provides
        //Provides cash dependency
    Cash provideCash(Soldiers soldiers){
        if (soldiers.getSoldier().equals("Warrior")) {return new Cash(200);} else return new Cash(300);
    }


}
