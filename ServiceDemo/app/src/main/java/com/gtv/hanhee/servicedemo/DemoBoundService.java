package com.gtv.hanhee.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class DemoBoundService extends Service {
    IBinder iBinder = new LocalBinder(); // lien ket den client
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class LocalBinder extends Binder {
        LocalBinder getLocalBinder() {
            return LocalBinder.this; // phuong thuc khoi tao khi client goi toi cac phuong thuc cua service
        }

    }


    public String XinChao() {
        return "Xin Chao cac ban";
    }

}
