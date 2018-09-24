package com.gtv.hanhee.dagger2.View;

import android.app.Application;

import com.gtv.hanhee.dagger2.Module.AppModule;
import com.gtv.hanhee.dagger2.Module.DaggerNetComponent;
import com.gtv.hanhee.dagger2.Module.NetComponent;
import com.gtv.hanhee.dagger2.Module.NetModule;

public class MyApplication extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;

    }
}
