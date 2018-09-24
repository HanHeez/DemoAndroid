package com.gtv.hanhee.dagger2project.Mock;

import com.gtv.hanhee.dagger2project.Example.ExamComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MockModule.class)
public interface MockComponent extends ExamComponent {

}
