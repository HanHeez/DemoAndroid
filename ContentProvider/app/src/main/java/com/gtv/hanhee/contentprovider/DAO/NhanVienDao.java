package com.gtv.hanhee.contentprovider.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.gtv.hanhee.contentprovider.Model.NhanVien;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NhanVienDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void themNhanVien(NhanVien... nhanViens);

    @Query("SELECT * FROM "+ NhanVien.TABLE_NAME)
    public Cursor layToanBoNhanVien();

    @Query("SELECT * FROM "+ NhanVien.TABLE_NAME +" WHERE "+ NhanVien.COLUMN_NAME +" LIKE :search")
    public Cursor layThongTinNhanVienTheoTen(String search);

    @Query("SELECT * FROM "+ NhanVien.TABLE_NAME +" WHERE id = :search")
    public Cursor layThongTinNhanVienTheoID(long search);
}
