package com.gtv.hanhee.dagger2._GameOfThrone.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gtv.hanhee.dagger2.R;
import com.gtv.hanhee.dagger2._GameOfThrone.Interface.BattleComponent;
import com.gtv.hanhee.dagger2._GameOfThrone.Interface.DaggerBattleComponent;
import com.gtv.hanhee.dagger2._GameOfThrone.Model.Cash;
import com.gtv.hanhee.dagger2._GameOfThrone.Model.Soldiers;
import com.gtv.hanhee.dagger2._GameOfThrone.Module.BraavosModule;

public class BattleOfBastardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_of_bastards);

        //        Mannual DI
//        Starks starks = new Starks();
//        Boltons boltons = new Boltons();
//        War war = new War(starks,boltons);
//        war.prepare();
//        war.report();

        //      Using Dagger 2
//        BattleComponent component = DaggerBattleComponent.create();

        Cash cash = new Cash(500);
        Soldiers soldiers = new Soldiers("Warrior");

        BattleComponent component = DaggerBattleComponent.builder().braavosModule(new BraavosModule(cash, soldiers)).build();
        War war = component.getWar();

//        war.prepare();
//        war.report();
//
//        Boltons boltons = component.getBoltons();
//        boltons.prepareForWar();

        Soldiers currentSoldier = component.getSoldiers();
        Cash currentCash = component.getCash();

        Log.d("checkwar", currentCash.getCash()+""+currentSoldier.getSoldier());

    }
}
