package com.gtv.hanhee.dagger2project.Example;

import com.gtv.hanhee.dagger2project.Interface.MyExample;

import java.util.Date;

public class Example implements MyExample {

    private long mDate;

    public Example() {
        mDate = new Date().getTime();
    }

    @Override
    public long getDate() {
        return mDate;
    }
}
