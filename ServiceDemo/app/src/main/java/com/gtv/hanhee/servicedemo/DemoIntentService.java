package com.gtv.hanhee.servicedemo;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class DemoIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DemoIntentService() {
        super("Intent Service");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this) {
            int dem = 0;
            while (dem < 10) {
                try {
                    wait(1000);
                    Log.d("dem intent service",""+dem);
                    dem++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Intent service da dung", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
