package com.gtv.hanhee.ungdungquanan.View.ManHinhTrangChu;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gtv.hanhee.ungdungquanan.Adapter.AdapterBinhLuan;
import com.gtv.hanhee.ungdungquanan.Adapter.GlideApp;
import com.gtv.hanhee.ungdungquanan.Model.QuanAnModel;
import com.gtv.hanhee.ungdungquanan.Model.TienIchModel;
import com.gtv.hanhee.ungdungquanan.Presenter.ManHinhChiTietQuanAn.ChiTietQuanAnPresenter;
import com.gtv.hanhee.ungdungquanan.R;
import com.gtv.hanhee.ungdungquanan.View.ChiDuong.DanDuongToiQuanAnActivity;
import com.gtv.hanhee.ungdungquanan.View.Wifi.CapNhatDanhSachWifiActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAnActivity extends AppCompatActivity implements OnMapReadyCallback,View.OnClickListener {

    TextView txtTenQuanAn, txtDiaChi, txtThoiGianHoatDong, txtTrangThaiHoatDong, txtTieudeToolbar,
            txtTongSoHinhAnh, txtTongSoBinhLuan, txtTongSoCheckin, txtTongSoLuuLai, txtGioihangia, txtDiaChiQa,
            txtTenWifi, txtMatkhauWifi, txtNgaydangWifi;
    ImageView imgHinhQuanAn;
    QuanAnModel quanAnModel;
    Toolbar toolbar;
    RecyclerView recyclerViewBinhluan;
    AdapterBinhLuan adapterBinhLuan;
    NestedScrollView nestedScrollViewChiTiet;
    GoogleMap googleMap;
    MapFragment mapFragment;
    LinearLayout khungTienich,khungWifi;
    ChiTietQuanAnPresenter chiTietQuanAnPresenter;
    View khunggooglemap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mainchitietquanan);
        Bundle b = getIntent().getBundleExtra("MY_PARAMS");
        quanAnModel = b.getParcelable("quanan");
        AddControls();
        toolbar = (Toolbar) findViewById(R.id.toolbarChitiet);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nestedScrollViewChiTiet = (NestedScrollView) findViewById(R.id.nestScrollChiTiet);
        Hienthichitietquanan();
        khunggooglemap.setOnClickListener(this);
    }

    private void AddControls() {
        txtTenQuanAn = (TextView) findViewById(R.id.txtDiaChiQuanAn);
        txtDiaChiQa = (TextView) findViewById(R.id.txtDiachiQa);
        txtDiaChi = (TextView) findViewById(R.id.txtDiaChiQuanAnChiTiet);
        txtThoiGianHoatDong = (TextView) findViewById(R.id.txtThoiGianHoatDong);
        txtTrangThaiHoatDong = (TextView) findViewById(R.id.txtTrangThaiHoatDong);
        txtTongSoHinhAnh = (TextView) findViewById(R.id.txtTongSoHinhAnh);
        txtTongSoBinhLuan = (TextView) findViewById(R.id.txtTongSoBinhLuan);
        txtTongSoCheckin = (TextView) findViewById(R.id.txtTongSoCheckin);
        txtTongSoLuuLai = (TextView) findViewById(R.id.txtTongSoLuuLai);
        txtTieudeToolbar = (TextView) findViewById(R.id.txtTieuDeToolbar);
        imgHinhQuanAn = (ImageView) findViewById(R.id.imgHinhQuanAn);
        txtGioihangia = (TextView) findViewById(R.id.txtGioiHanGia);
        recyclerViewBinhluan = (RecyclerView) findViewById(R.id.recyclerBinhLuanChiTiet);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        khungTienich = (LinearLayout) findViewById(R.id.khungTienich);
        txtTenWifi = (TextView) findViewById(R.id.txtTenWifi);
        txtMatkhauWifi = (TextView) findViewById(R.id.txtMatkhauWifi);
        khungWifi = (LinearLayout) findViewById(R.id.khungWifi);
        khunggooglemap = (View) findViewById(R.id.khunggooglemap);
        txtNgaydangWifi = (TextView) findViewById(R.id.txtNgaydangWifi);
        chiTietQuanAnPresenter = new ChiTietQuanAnPresenter();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void Hienthichitietquanan() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String giohientai = dateFormat.format(calendar.getTime());
        String giomocua = quanAnModel.getGiomocua();
        String giodongcua = quanAnModel.getGiodongcua();

        try {
            Date dateHientai = dateFormat.parse(giohientai);
            Date dateMocua = dateFormat.parse(giomocua);
            Date dateDongcua = dateFormat.parse(giodongcua);
            if (dateHientai.after(dateMocua) && dateHientai.before(dateDongcua)) {
                txtTrangThaiHoatDong.setText(getString(R.string.dangmocua));
            } else {
                txtTrangThaiHoatDong.setText(getString(R.string.dadongcua));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtTieudeToolbar.setText(quanAnModel.getTenquanan());
        txtTenQuanAn.setText(quanAnModel.getTenquanan());
        txtDiaChiQa.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
        txtDiaChi.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
        txtThoiGianHoatDong.setText(quanAnModel.getGiomocua() + " - " + quanAnModel.getGiodongcua());
        txtTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size() + "");
        txtTongSoBinhLuan.setText(quanAnModel.getBinhluanlist().size() + " ");

        DownloadTienich(khungTienich);

        if (quanAnModel.getGiatoida() != 0 & quanAnModel.getGiatoithieu() != 0) {
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String giatoida = numberFormat.format(quanAnModel.getGiatoida()) + " đ";
            String giatoithieu = numberFormat.format(quanAnModel.getGiatoithieu()) + " đ";
            txtGioihangia.setText(giatoithieu + " - " + giatoida);
        } else {
            txtGioihangia.setVisibility(View.INVISIBLE);
        }

        StorageReference storageHinhanh = FirebaseStorage.getInstance().getReference()
                .child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
        GlideApp.with(this).load(storageHinhanh).transition(new DrawableTransitionOptions().crossFade(200)).into(imgHinhQuanAn);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBinhluan.setLayoutManager(layoutManager);
        recyclerViewBinhluan.setHasFixedSize(true);
        recyclerViewBinhluan.setNestedScrollingEnabled(false);
        adapterBinhLuan = new AdapterBinhLuan(this, quanAnModel.getBinhluanlist(), R.layout.custom_layout_binhluan);
        recyclerViewBinhluan.setAdapter(adapterBinhLuan);
        adapterBinhLuan.notifyDataSetChanged();

        chiTietQuanAnPresenter.HienThiDanhSachWifiQuanAn(quanAnModel.getMaquanan(),txtTenWifi,txtMatkhauWifi,txtNgaydangWifi);
        khungWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDanhSachWifi = new Intent(ChiTietQuanAnActivity.this, CapNhatDanhSachWifiActivity.class);
                iDanhSachWifi.putExtra("maquanan",quanAnModel.getMaquanan());
                startActivity(iDanhSachWifi);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        double latitude = quanAnModel.getChiNhanhQuanAnModelList().get(0).getLatitude();
        double longitude = quanAnModel.getChiNhanhQuanAnModelList().get(0).getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Marker"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
        googleMap.moveCamera(cameraUpdate);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
    }

    private void DownloadTienich(final LinearLayout khungtienich) {
        if (quanAnModel.getTienich()!=null) {
            for (String matienich : quanAnModel.getTienich()) {
                DatabaseReference nodeTienich = FirebaseDatabase.getInstance().getReference().child("quanlytienichs").child(matienich);
                nodeTienich.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        TienIchModel tienIchModel = dataSnapshot.getValue(TienIchModel.class);
                        ImageView imageTienich = new ImageView(ChiTietQuanAnActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
                        layoutParams.setMargins(10, 10, 10, 10);
                        imageTienich.setLayoutParams(layoutParams);
                        imageTienich.setPadding(5, 5, 5, 5);
                        imageTienich.setScaleType(ImageView.ScaleType.FIT_XY);
                        StorageReference storageHinhanh = FirebaseStorage.getInstance().getReference()
                                .child("hinhtienich").child(tienIchModel.getHinhtienich());
                        GlideApp.with(ChiTietQuanAnActivity.this).load(storageHinhanh).transition(new DrawableTransitionOptions().crossFade(200)).into(imageTienich);
                        khungtienich.addView(imageTienich);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.khunggooglemap:
                Intent iDanduong = new Intent(ChiTietQuanAnActivity.this, DanDuongToiQuanAnActivity.class);
                iDanduong.putExtra("latitude", quanAnModel.getChiNhanhQuanAnModelList().get(0).getLatitude());
                iDanduong.putExtra("longitude", quanAnModel.getChiNhanhQuanAnModelList().get(0).getLongitude());
                startActivity(iDanduong);
                break;
        }
    }
}
