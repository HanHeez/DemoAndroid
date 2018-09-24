package com.gtv.hanhee.dagger2project.Mock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockModule {

    @Provides
    @Singleton
    static Mock provideExample() {
        return new Mock();
    }
}
