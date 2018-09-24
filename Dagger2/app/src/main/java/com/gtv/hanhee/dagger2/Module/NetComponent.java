package com.gtv.hanhee.dagger2.Module;

import com.gtv.hanhee.dagger2.View.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}
