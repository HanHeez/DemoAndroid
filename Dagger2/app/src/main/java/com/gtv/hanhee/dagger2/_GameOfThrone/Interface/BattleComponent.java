package com.gtv.hanhee.dagger2._GameOfThrone.Interface;

import com.gtv.hanhee.dagger2._GameOfThrone.Model.Cash;
import com.gtv.hanhee.dagger2._GameOfThrone.Model.Soldiers;
import com.gtv.hanhee.dagger2._GameOfThrone.Module.BraavosModule;
import com.gtv.hanhee.dagger2._GameOfThrone.View.Boltons;
import com.gtv.hanhee.dagger2._GameOfThrone.View.Starks;
import com.gtv.hanhee.dagger2._GameOfThrone.View.War;

import dagger.Component;

@Component(modules = BraavosModule.class)
public interface BattleComponent {
    War getWar();
    Cash getCash();
    Soldiers getSoldiers();
}
