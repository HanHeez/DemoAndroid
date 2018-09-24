package com.gtv.hanhee.contentprovider;

import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gtv.hanhee.contentprovider.Database.NhanVienDatabase;
import com.gtv.hanhee.contentprovider.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    List<NhanVien> listNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("com.gtv.hanhee.contentprovider.NhanVienProvider/nhanviens");

        Cursor cursor = contentResolver.query(uri, null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.d("dulieuprovider",cursor.getString(1));
            cursor.moveToNext();
        }
    }

    private void addData() {
        NhanVienDatabase db = NhanVienDatabase.getDatabase(this);

        db.clearAllTables();

        listNhanVien = new ArrayList<>();

        NhanVien nv1 = new NhanVien();
        nv1.name = "Long";
        nv1.birthday = "1987";
        NhanVien nv2 = new NhanVien();
        nv2.name = "HanHee";
        nv2.birthday = "1991";
        NhanVien nv3 = new NhanVien();
        nv2.name = "Long";
        nv2.birthday = "1994";

        listNhanVien.add(nv1);
        listNhanVien.add(nv2);
        listNhanVien.add(nv3);

        db.nhanVienDao().themNhanVien(nv1,nv2,nv3);
        Cursor thongTinNVCursor = db.nhanVienDao().layThongTinNhanVienTheoTen("Long");

    }

}
