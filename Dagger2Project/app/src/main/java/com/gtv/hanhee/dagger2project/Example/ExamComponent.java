package com.gtv.hanhee.dagger2project.Example;

import com.gtv.hanhee.dagger2project.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ExamModule.class)
public interface ExamComponent {
    void inject(MainActivity mainActivity);
}
