package com.gtv.hanhee.googlecloudmessage;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.firebase.iid.FirebaseInstanceId;

public class RegistrationService extends IntentService {

    public RegistrationService() {
        super("Dang ky ID Client App");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {



    }
}
