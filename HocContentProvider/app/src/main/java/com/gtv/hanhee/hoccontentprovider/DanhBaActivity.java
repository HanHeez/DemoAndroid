package com.gtv.hanhee.hoccontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gtv.hanhee.model.Contact;

import java.util.ArrayList;

public class DanhBaActivity extends AppCompatActivity {

    ListView lvDanhba;
    ArrayList<Contact> dsDanhba;
    ArrayAdapter<Contact> adapterDanhba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        addControls();
        addEvents();
        ShowAllContactFromDevice();
    }

    private void ShowAllContactFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        dsDanhba.clear();
        while (cursor.moveToNext()) {
            String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int vitriTenCotName = cursor.getColumnIndex(tenCotName);
            int vitriTenCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(vitriTenCotName);
            String phone = cursor.getString(vitriTenCotPhone);
            Contact contact = new Contact(name,phone);
            dsDanhba.add(contact);
        }
        adapterDanhba.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvDanhba = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhba = new ArrayList<>();
        adapterDanhba = new ArrayAdapter<Contact>(
                DanhBaActivity.this,
                android.R.layout.simple_list_item_1,
                dsDanhba
        );
        lvDanhba.setAdapter(adapterDanhba);
    }
}
