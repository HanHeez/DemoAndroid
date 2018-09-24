package com.gtv.hanhee.ungdungquanan.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.gtv.hanhee.ungdungquanan.Interface.ChiTietQuanAnInterface;

public class WifiQuanAnModel {
    String ten, matkhau;
    String ngaydang;

    public WifiQuanAnModel() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    DatabaseReference nodeWifiQuanAn;
    public void LayDanhSachWifiQuanAn(String maquan, final ChiTietQuanAnInterface chiTietQuanAnInterface) {
        Query nodeWifiQuanAn = FirebaseDatabase.getInstance().getReference().child("wifiquanans").child(maquan).orderByKey();
        nodeWifiQuanAn.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot valueWifi: dataSnapshot.getChildren()) {
                    WifiQuanAnModel wifiQuanAnModel = valueWifi.getValue(WifiQuanAnModel.class);
                   chiTietQuanAnInterface.HienThiDanhSachWifiQuanAn(wifiQuanAnModel);
                }
                WifiQuanAnModel wifiQuanAnModel = dataSnapshot.getValue(WifiQuanAnModel.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void ThemWifiQuanAn(final Context context, WifiQuanAnModel wifiQuanAnModel, String maquanan) {
        DatabaseReference dataNodewifiquanan = FirebaseDatabase.getInstance().getReference().child("wifiquanans").child(maquanan);
        dataNodewifiquanan.push().setValue(wifiQuanAnModel, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
