package com.gtv.hanhee.ungdungquanan.Presenter.DanDuongGoogleMap;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DanDuongToiQuanAnPresenter {
    ParserPolyline parserPolyline;
    DownloadPolyline downloadPolyline;
    PolylineOptions polylineOptions;

    public DanDuongToiQuanAnPresenter() {
        parserPolyline = new ParserPolyline();
        downloadPolyline = new DownloadPolyline();
    }

    public void HienThiDanDuongToiQuanAn(GoogleMap googleMap,String duongdan) {

        downloadPolyline.execute(duongdan);
        polylineOptions = new PolylineOptions();

        try {
            String dataJson = downloadPolyline.get();
            List<LatLng> latLngList= parserPolyline.getDanhSachToaDo(dataJson);

            for (LatLng toado: latLngList) {
                polylineOptions.add(toado);
            }

            Polyline polyline = googleMap.addPolyline(polylineOptions);
            polyline.setWidth(3);

//            List<LatLng>
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
