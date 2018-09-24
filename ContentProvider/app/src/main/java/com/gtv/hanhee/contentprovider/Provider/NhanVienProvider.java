package com.gtv.hanhee.contentprovider.Provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gtv.hanhee.contentprovider.DAO.NhanVienDao;
import com.gtv.hanhee.contentprovider.Database.NhanVienDatabase;

public class NhanVienProvider extends ContentProvider {

    public static String AUTHOR = "com.gtv.hanhee.contentprovider.NhanVienProvider";
    public static String TABLE_NHANVIEN = "nhanviens";
    public static String TABLE_CONGTY = "congtys";
    NhanVienDatabase db;
    Cursor cursor;
    Context context;

    private static String URI_TABLE_NHANVIEN = "content://" + AUTHOR + "/" +TABLE_NHANVIEN;


    public static UriMatcher uriMatcher;
    @Override
    public boolean onCreate() {
        context = getContext();
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHOR, TABLE_NHANVIEN, 1);
        uriMatcher.addURI(AUTHOR, TABLE_NHANVIEN + "/#", 2);
        uriMatcher.addURI(AUTHOR, TABLE_NHANVIEN , 3);
        db = NhanVienDatabase.getDatabase(getContext());

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int matcher = uriMatcher.match(uri);
        NhanVienDao nvDao = NhanVienDatabase.getDatabase(getContext()).nhanVienDao();
        switch (matcher) {
            case 1:
                cursor = nvDao.layToanBoNhanVien();
                break;
            case 2:
                cursor = nvDao.layThongTinNhanVienTheoID(ContentUris.parseId(uri));
                break;
            case 3:

                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
