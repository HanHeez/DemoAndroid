package com.gtv.hanhee.contentprovider.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.graphics.Bitmap;

@Entity(tableName = NhanVien.TABLE_NAME, indices = {@Index(value={"ten_nhanvien", "ngaysinh"})})
public class NhanVien {

    public static final String TABLE_NAME = "nhanviens";

    public static final String COLUMN_NAME = "ten_nhanvien";

    public static final String COLUMN_BIRTHDAY = "ngaysinh";
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = COLUMN_NAME)
    public String name;
    @ColumnInfo(name = COLUMN_BIRTHDAY)
    public String birthday;

    @Ignore
    String profilePicture;

    public static NhanVien fromContentValues(ContentValues values) {
        final NhanVien nv = new NhanVien();
        if (values.containsKey(COLUMN_NAME)) {
            nv.name = values.getAsString(COLUMN_NAME);
        }
        if (values.containsKey(COLUMN_BIRTHDAY)) {
            nv.name = values.getAsString(COLUMN_BIRTHDAY);
        }
        return nv;
    }

}
