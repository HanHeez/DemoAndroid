package com.gtv.hanhee.contentprovider.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.gtv.hanhee.contentprovider.DAO.NhanVienDao;
import com.gtv.hanhee.contentprovider.Model.NhanVien;

@Database(entities = {NhanVien.class}, version = 1)
public abstract class NhanVienDatabase extends RoomDatabase {
    public abstract NhanVienDao nhanVienDao();

    private static NhanVienDatabase INSTANCE;

    public static NhanVienDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NhanVienDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NhanVienDatabase.class, "nhanviens")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}




