package com.gtv.hanhee.dagger2project.Example;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ExamModule {

    @Provides
    @Singleton
    static Example provideExample() {
        return new Example();
    }

}
