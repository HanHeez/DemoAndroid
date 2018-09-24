package com.gtv.hanhee.dagger2project;

import android.app.Application;
import com.gtv.hanhee.dagger2project.Example.DaggerExamComponent;
import com.gtv.hanhee.dagger2project.Example.ExamComponent;
import com.gtv.hanhee.dagger2project.Example.ExamModule;

public class MyApplication extends Application {
    private ExamComponent examComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        examComponent = createExamComponent();
    }

    ExamComponent getExamComponent() {
        return examComponent;
    }

    private ExamComponent createExamComponent() {
        return DaggerExamComponent.builder().examModule(new ExamModule()).build();
    }

    void setExamComponent(ExamComponent examComponent) {
        this.examComponent = examComponent;
    }
}
