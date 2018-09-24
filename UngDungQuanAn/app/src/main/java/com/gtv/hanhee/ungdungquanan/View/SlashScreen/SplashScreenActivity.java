package com.gtv.hanhee.ungdungquanan.View.SlashScreen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.gtv.hanhee.ungdungquanan.Presenter.SplashScreen.MyPresenterSplashScreen;
import com.gtv.hanhee.ungdungquanan.R;
import com.gtv.hanhee.ungdungquanan.Utils.LayHashKey;
import com.gtv.hanhee.ungdungquanan.View.ManHinhDangNhap.DangNhapActivity;

public class SplashScreenActivity extends AppCompatActivity implements ViewSplashScreen {

    public static final int REQUEST_PERMISSIONS_LOCATION = 1;
    public static final int REQUEST_PERMISSIONS_LOCATION2 = 2;
    TextView txtVersion, txtTenCongty, txtLoading;
    MyPresenterSplashScreen mPresenter;
    LayHashKey layHashKey;
    SharedPreferences sharedPreferences;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_splashscreen);
        mPresenter = new MyPresenterSplashScreen(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        sharedPreferences = getSharedPreferences("toado", MODE_PRIVATE);
        mPresenter.XuLyDuLieu();

    }

    @Override
    public void CheckPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_PERMISSIONS_LOCATION2);

        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                saveCurrentLocation(location);
                            }
                        }
                    });
        }


    }

    public void saveCurrentLocation(Location location) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("latitude", String.valueOf(location.getLatitude()));
        editor.putString("longitude", String.valueOf(location.getLongitude()));
        editor.commit();
        TruyCapTrangDangNhap();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mFusedLocationClient.getLastLocation()
                            .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        saveCurrentLocation(location);
                                    }
                                }
                            });
                } else {

                }

            }
        }
    }

    @Override
    public void AddControls() {
        txtVersion = (TextView) findViewById(R.id.txtVersion);
        txtTenCongty = (TextView) findViewById(R.id.txtTenCongty);
        txtLoading = (TextView) findViewById(R.id.txtLoading);


    }

    @Override
    public void LayTenVersion() {
        txtVersion.setText(getString(R.string.version));
        txtTenCongty.setText(getString(R.string.TenCongty));
        txtLoading.setText(getString(R.string.Loading));
        layHashKey.GetKeyHash(this);

    }

    @Override
    public void TruyCapTrangDangNhap() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iDangnhap = new Intent(SplashScreenActivity.this, DangNhapActivity.class);
                startActivity(iDangnhap);
                finish();

            }
        }, 2000);

    }


}
