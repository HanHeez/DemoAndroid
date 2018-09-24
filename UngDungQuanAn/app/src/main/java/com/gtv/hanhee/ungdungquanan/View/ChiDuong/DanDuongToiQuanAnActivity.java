package com.gtv.hanhee.ungdungquanan.View.ChiDuong;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gtv.hanhee.ungdungquanan.Presenter.DanDuongGoogleMap.DanDuongToiQuanAnPresenter;
import com.gtv.hanhee.ungdungquanan.R;

public class DanDuongToiQuanAnActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap googleMap;
    MapFragment mapFragment;
    SharedPreferences sharedPreferences;

    DanDuongToiQuanAnPresenter danDuongToiQuanAnPresenter;

    //https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyB9iq7w_opHJ2SfmEGZywUUZmjOVs3M5qs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danduong_map_toiquanan);

        danDuongToiQuanAnPresenter = new DanDuongToiQuanAnPresenter();

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.clear();
        Double latitude = getIntent().getDoubleExtra("latitude",0);
        Double longitude = getIntent().getDoubleExtra("longitude",0);
        LatLng vitriquanan = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions().position(vitriquanan));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(vitriquanan, 14);
        googleMap.moveCamera(cameraUpdate);

        sharedPreferences = getSharedPreferences("toado", Context.MODE_PRIVATE);
        Location vitrihientai = new Location("");
        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude", "0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude", "0")));

        LatLng vthientai = new LatLng(vitrihientai.getLatitude(), vitrihientai.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(vthientai));
        String duongdan = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                vitrihientai.getLatitude() + "," + vitrihientai.getLongitude() + "&destination=" +
                latitude + "," + longitude +
                "&key=AIzaSyB9iq7w_opHJ2SfmEGZywUUZmjOVs3M5qs";
        Log.d("duongdan",duongdan);
        danDuongToiQuanAnPresenter.HienThiDanDuongToiQuanAn(googleMap, duongdan);
    }
}
