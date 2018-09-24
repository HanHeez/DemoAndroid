package com.gtv.hanhee.googlemap;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    MapFragment mapFragment;
    GoogleMap map, nMap;
    Button btnThemMarker,btnPolyline,btnPolygon,btnCircle;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        MapFragment mapFragment1 = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//        mapFragment1.getMapAsync(this);

        btnThemMarker = (Button) findViewById(R.id.btnThemMarker);
        btnPolyline = (Button) findViewById(R.id.btnThemPolyline);
        btnPolygon = (Button) findViewById(R.id.btnThemPolygon);
        btnCircle = (Button) findViewById(R.id.btnThemCircle);
        btnThemMarker.setOnClickListener(this);
        btnPolyline.setOnClickListener(this);
        btnPolygon.setOnClickListener(this);
        btnCircle.setOnClickListener(this);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thong bao");
        progressDialog.setMessage("Dang tai map vui long cho");
        progressDialog.show();

            }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        progressDialog.dismiss();
        map = googleMap;
        nMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);



        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MainActivity.this, ""+marker.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

//    private void setLocation() {
//        LatLng docvinhhung = new LatLng(20.998782,105.882217);
//        nMap.addMarker(new MarkerOptions().position(docvinhhung)
//                .title("25 ngo 37 dai dong")
//                .snippet("Nha rieng HanHee")
//        );
//        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(docvinhhung,18));
//    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThemMarker:
                ThemMarker();
                break;
            case R.id.btnThemPolyline:
                ThemPolyline();

                break;
            case R.id.btnThemPolygon:
                Polygon polygon = map.addPolygon(new PolygonOptions()
                        .add(new LatLng(20.998782, 105.882217), new LatLng(21.0080617,105.8512351)
                                , new LatLng(21.008237,105.841429),
                                new LatLng(21.006016,105.8346537))
                        .strokeColor(Color.RED)
                        .fillColor(Color.BLUE));

                break;
            case R.id.btnThemCircle:
                Circle circle = map.addCircle(new CircleOptions()
                        .center(new LatLng(20.998782, 105.882217))
                        .radius(1000)
                        .strokeColor(Color.RED));
                break;
        }

    }

    private void ThemPolyline() {
        PolylineOptions polylineOptions = new PolylineOptions();
        LatLng docvinhhung = new LatLng(20.998782, 105.882217);
        LatLng vitri2 = new LatLng(21.0080617,105.8512351);
        LatLng vitri3 = new LatLng(21.008237,105.841429);
        LatLng vitri4 = new LatLng(21.006016,105.8346537);
        polylineOptions.add(docvinhhung);
        polylineOptions.add(vitri2);
        polylineOptions.add(vitri3);
        polylineOptions.add(vitri4);
        map.addPolyline(polylineOptions);
    }

    private void ThemMarker() {
        LatLng docvinhhung = new LatLng(20.998782, 105.882217);
        MarkerOptions vitri1 = new MarkerOptions();
        vitri1.draggable(true);
        vitri1.position(docvinhhung);
        vitri1.title("Số 25 Ngõ 37 Đại Đồng");
        vitri1.snippet("Đây là nhà riêng của HanHee");
        Marker marker = map.addMarker(vitri1);


//        CameraUpdate moveCamera = CameraUpdateFactory.newLatLng(docvinhhung);
//        map.moveCamera(moveCamera);
//
//        CameraUpdate zoomCamera = CameraUpdateFactory.zoomBy(10);
//        map.animateCamera(zoomCamera,2000,null);
//
//        CameraUpdate movezoomCamera = CameraUpdateFactory.newLatLngZoom(docvinhhung,15);
//        map.animateCamera(movezoomCamera,2500,null);

        CameraPosition cameraPosition = CameraPosition.builder().
                target(docvinhhung).bearing(8.6f)
                .tilt(45)
                .zoom(15).build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate, 2000, null);
    }
}
